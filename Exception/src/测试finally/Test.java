package 测试finally;

public class Test {
    public static void main(String[] arge) throws Exception {
        try{
            throw new Exception("抛出异常");
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            //Thread.sleep(2000);  打开可以看出异常是在主方法外的一个线程
            System.out.println("最后执行");
        }
    }
}
