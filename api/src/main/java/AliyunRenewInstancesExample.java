import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.aliyuncs.AcsRequest;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesResponse;
import com.aliyuncs.ecs.model.v20140526.RenewInstanceRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class  AliyunRenewInstancesExample {

    private String accessKeyId  = "<AccessKey>";
    private String accessSecret = "<AccessSecret>";

    /**
     * 实例所属的地域ID
     */
    private String regionId = "cn-shanghai";
    /**
     * 指定的需要续费的实例 ID
     */
    private String[] instanceIds = new String[] {"i-uf6bo1bw09d6f7xmbk9l"};
    /**
     * 预付费续费时长
     */
    private Integer period = 4;
    /**
     * 续费时长单位，取值：Week/Month
     */
    private String periodUnit = "Month";

    private Map<String, InstanceExpiredModel> instanceExpiredModelMap = new HashMap<String, InstanceExpiredModel>();
    private static final ExecutorService RENEW_INSTANCE_POOL = Executors.newFixedThreadPool(500);
    private static final Integer CHECK_EXPIRED_TIME_OUT_MILLISECOND = 60 * 1000;
    private static final Integer CHECK_EXPIRED_TIME_INTERVAL_MILLISECOND = 2 * 1000;
    private static final String INSTANCE_CHARGE_TYPE_PREPAID = "PrePaid";
    private static final String DESCRIBE_INSTANCES_FILTER_KEY_EXPIRED_START_TIME = "ExpiredStartTime";
    private static final String DESCRIBE_INSTANCES_FILTER_KEY_EXPIRED_END_TIME = "ExpiredEndTime";

    public static void main(String[] args) {
        /**
         * 使用须知：
         * 调用 OpenAPI 续费会自动扣费，请谨慎调用
         * 您的账号必须支持账号余额支付或信用支付，请确保金额充足
         *
         * 调用续费API后会对 新的到期时间 和 原始的到期时间 进行比较并分别输出成功和失败的实例
         */
        new AliyunRenewInstancesExample().callRenewInstance();
    }

    private void callRenewInstance() {
        /**
         * 若需查询需要续费的实例，可查看 describeNeedRenewInstances 方法
         */
        //List<String> needRenewInstanceIds = describeNeedRenewInstances();
        List<String> instanceIdList = Arrays.asList(instanceIds);
        List<DescribeInstancesResponse.Instance> instances = describeInstances(instanceIdList);
        if (!checkInstanceIds(instances, instanceIdList)) {
            return;
        }
        updateInstanceOriginalExpiredTime(instances);
        for (final String instanceId : instanceIdList) {
            RENEW_INSTANCE_POOL.execute(new Runnable() {
                public void run() {
                    RenewInstanceRequest request = new RenewInstanceRequest();
                    request.setRegionId(regionId);
                    request.setInstanceId(instanceId);
                    request.setPeriod(period);
                    request.setPeriodUnit(periodUnit);
                    callOpenApi(request, instanceExpiredModelMap.get(instanceId));
                }
            });
        }
        try {
            RENEW_INSTANCE_POOL.shutdown();
            RENEW_INSTANCE_POOL.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkInstancesExpiredTime(instanceIdList);
    }
    /**
     * 每2秒中检查一次实例的到期时间，超时时间设为1分钟
     *
     * @param instanceIds 需要检查的实例ID
     */
    private void checkInstancesExpiredTime(List<String> instanceIds) {
        Long startTime = System.currentTimeMillis();
        for (;;) {
            boolean allCompleted = true;
            Long timeStamp = System.currentTimeMillis();
            updateInstanceNewExpiredTime(instanceIds);
            for (String instanceId : instanceExpiredModelMap.keySet()) {
                InstanceExpiredModel instanceExpiredModel = instanceExpiredModelMap.get(instanceId);
                if (instanceExpiredModel.newExpiredTime == null && instanceExpiredModel.errorCode == null) {
                    allCompleted = false;
                }
            }
            if (allCompleted) {
                logRenewResult();
                return;
            }
            if (timeStamp - startTime > CHECK_EXPIRED_TIME_OUT_MILLISECOND) {
                logInfo(String.format("Check instance new expiredTime timeout. Because it takes too much time, View the result detail: %s",
                        JSON.toJSONString(instanceExpiredModelMap, SerializerFeature.PrettyFormat)));
                return;
            }
            sleepSomeTime(CHECK_EXPIRED_TIME_INTERVAL_MILLISECOND);
        }
    }

    private boolean checkInstanceIds(List<DescribeInstancesResponse.Instance> instances,
                                     List<String> instanceIds) {
        if (instances.size() != instanceIds.size()) {
            List<String> responseInstanceIds = new ArrayList<String>();
            List<String> invalidInstanceIds = new ArrayList<String>();
            for (DescribeInstancesResponse.Instance instance : instances) {
                responseInstanceIds.add(instance.getInstanceId());
            }
            for (String instanceId : instanceIds) {
                if (!responseInstanceIds.contains(instanceId)) {
                    invalidInstanceIds.add(instanceId);
                }
            }
            logInfo(String.format("Fail. CheckInstanceIds failure. Invalid InstanceIds: %s",
                    JSON.toJSONString(invalidInstanceIds)));
            return false;
        }
        return true;
    }

    /**
     * 记录 instance 原始到期时间，以便在调用续费 OpenAPI 后 check 到期时间是否发生变化来判断续费是否成功
     *
     * @param instances
     * @return
     */
    private boolean updateInstanceOriginalExpiredTime(List<DescribeInstancesResponse.Instance> instances) {
        for (DescribeInstancesResponse.Instance instance : instances) {
            InstanceExpiredModel instanceExpiredModel = new InstanceExpiredModel();
            instanceExpiredModel.instanceId = instance.getInstanceId();
            instanceExpiredModel.originalExpiredTime = instance.getExpiredTime();
            instanceExpiredModelMap.put(instance.getInstanceId(), instanceExpiredModel);
        }
        return true;
    }

    private void updateInstanceNewExpiredTime(List<String> instanceIds) {
        List<DescribeInstancesResponse.Instance> instances = describeInstances(instanceIds);
        for (DescribeInstancesResponse.Instance instance : instances) {
            InstanceExpiredModel instanceExpiredModel = instanceExpiredModelMap.get(instance
                    .getInstanceId());
            if (!instance.getExpiredTime().equals(instanceExpiredModel.originalExpiredTime)) {
                instanceExpiredModel.newExpiredTime = instance.getExpiredTime();
            }
        }
    }

    private List<DescribeInstancesResponse.Instance> describeInstances(List<String> instanceIds) {
        int offset = 0;
        int pageSize = 100;
        int totalCount = instanceIds.size();
        List<DescribeInstancesResponse.Instance> instances = new ArrayList<DescribeInstancesResponse.Instance>();
        while (offset < totalCount) {
            DescribeInstancesRequest describeInstancesRequest = new DescribeInstancesRequest();
            describeInstancesRequest.setRegionId(regionId);
            describeInstancesRequest.setPageSize(pageSize);
            describeInstancesRequest.setInstanceChargeType(INSTANCE_CHARGE_TYPE_PREPAID);
            describeInstancesRequest.setInstanceIds(JSON.toJSONString(instanceIds.subList(offset,
                    Math.min(totalCount, offset + pageSize))));
            DescribeInstancesResponse response = callOpenApi(describeInstancesRequest, null);
            if (response == null || response.getInstances().size() == 0) {
                logInfo(String.format("Fail. Can not find instance. InstanceIds: %s", JSON.toJSONString(instanceIds)));
                return instances;
            }
            instances.addAll(response.getInstances());
            offset += response.getPageSize();
        }
        return instances;
    }

    private Map<String, InstanceExpiredModel> getFailedInstances() {
        Map<String, InstanceExpiredModel> failedInstances = new HashMap<String, InstanceExpiredModel>();
        for (String instanceId : instanceExpiredModelMap.keySet()) {
            InstanceExpiredModel instanceExpiredModel = instanceExpiredModelMap.get(instanceId);
            if (instanceExpiredModel.errorCode != null) {
                failedInstances.put(instanceId, instanceExpiredModel);
            }
        }
        return failedInstances;
    }

    private Map<String, InstanceExpiredModel> getSuccessInstances() {
        Map<String, InstanceExpiredModel> successInstances = new HashMap<String, InstanceExpiredModel>();
        for (String instanceId : instanceExpiredModelMap.keySet()) {
            InstanceExpiredModel instanceExpiredModel = instanceExpiredModelMap.get(instanceId);
            if (instanceExpiredModel.errorCode == null
                    && instanceExpiredModel.newExpiredTime != null
                    && !instanceExpiredModel.originalExpiredTime
                    .equals(instanceExpiredModel.newExpiredTime)) {
                successInstances.put(instanceId, instanceExpiredModel);
            }
        }
        return successInstances;
    }

    /**
     * 调用OpenAPI的方法，这里进行了错误处理
     *
     * @param request AcsRequest, Open API的请求
     * @param <T> AcsResponse 请求所对应返回值
     * @return 返回OpenAPI的调用结果. 如果调用失败，则会返回null
     */
    private <T extends AcsResponse> T callOpenApi(AcsRequest<T> request, BaseResult errorResult) {
        IAcsClient client = initClient();
        try {
            T response = client.getAcsResponse(request, false, 0);
            logInfo(String.format("Success. OpenAPI Action: %s call successfully.",
                    request.getActionName()));
            return response;
        } catch (ClientException e) {
            buildErrResult(errorResult, e);
        }
        return null;
    }

    /**
     * 需要填充账号的AccessKey ID，以及账号的Access Key Secret
     */
    private IAcsClient initClient() {
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        return new DefaultAcsClient(profile);
    }

    private void logRenewResult() {
        logInfo("-------- Renew Instances Result --------");
        Map<String, InstanceExpiredModel> failedInstances = getFailedInstances();
        if (failedInstances.size() > 0) {
            logInfo(String.format("Fail. Some instances renew failure, result: %s",
                    JSON.toJSONString(failedInstances, SerializerFeature.PrettyFormat)));
            Map<String, InstanceExpiredModel> successInstances = getSuccessInstances();
            if (successInstances.size() > 0) {
                logInfo(String.format("Success. Some instances renew success, result: %s",
                        JSON.toJSONString(successInstances, SerializerFeature.PrettyFormat)));
            }
        } else {
            logInfo(String.format("Success. RenewInstance result: %s", JSON.toJSONString(
                    instanceExpiredModelMap, SerializerFeature.PrettyFormat)));
        }
    }

    private static void sleepSomeTime(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void buildErrResult(BaseResult errResult, ClientException e) {
        if (errResult != null) {
            errResult.errorCode = e.getErrCode();
            errResult.requestId = e.getRequestId();
        }
        if (e instanceof ServerException) {
            logInfo(String.format("Fail. Something with your connection with Aliyun go incorrect. ErrorCode: %s",
                    e.getErrCode()));
        } else {
            logInfo(String.format("Fail. Business error. ErrorCode: %s, RequestId: %s",
                    e.getErrCode(), e.getRequestId()));
        }
    }

    private static void logInfo(String message) {
        System.out.println(message);
    }

    private List<String> describeNeedRenewInstances() {
        String instanceExpiredStartTimeUtc = "2018-10-21T16:00Z";
        String instanceExpiredEndTimeUtc = "2018-12-01T16:00Z";
        List<String> instanceIds = new ArrayList<String>();

        int pageSize = 100;
        int pageNumber = 1;
        int totalCount = 1;
        while (instanceIds.size() < totalCount) {
            DescribeInstancesRequest describeInstancesRequest = new DescribeInstancesRequest();
            describeInstancesRequest.setRegionId(regionId);
            describeInstancesRequest.setFilter3Key(DESCRIBE_INSTANCES_FILTER_KEY_EXPIRED_START_TIME);
            describeInstancesRequest.setFilter3Value(instanceExpiredStartTimeUtc);
            describeInstancesRequest.setFilter4Key(DESCRIBE_INSTANCES_FILTER_KEY_EXPIRED_END_TIME);
            describeInstancesRequest.setFilter4Value(instanceExpiredEndTimeUtc);
            describeInstancesRequest.setInstanceChargeType(INSTANCE_CHARGE_TYPE_PREPAID);
            describeInstancesRequest.setPageSize(pageSize);
            describeInstancesRequest.setPageNumber(pageNumber);
            DescribeInstancesResponse response = callOpenApi(describeInstancesRequest, null);
            if (response == null || response.getTotalCount() == 0) {
                logInfo("Fail. DescribeNeedRenewInstances occurred error or response.getTotalCount() == 0");
                return instanceIds;
            }
            for (DescribeInstancesResponse.Instance instance : response.getInstances()) {
                instanceIds.add(instance.getInstanceId());
            }
            totalCount = response.getTotalCount();
            pageNumber++;
        }
        logInfo(String.format("Success. DescribeNeedRenewInstances result: %s", instanceIds.toString()));
        return instanceIds;
    }

    class BaseResult {
        public String errorCode;
        public String requestId;
    }

    class InstanceExpiredModel extends BaseResult {
        public String instanceId;
        public String originalExpiredTime;
        public String newExpiredTime;
    }
}
