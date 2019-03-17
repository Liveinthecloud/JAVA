package per.zengwei.friends;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CommonFriendsOne {
    public static class CommonFriendsOneMapper extends Mapper<LongWritable, Text,Text, Text>{

        /*
        A:B,C,D,F,E,O------>
        B->A    C->A    F->A    E->A    O->A
        */
        Text k=new Text();
        Text v=new Text();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //第一次通过“:”吧一行数据切分开来
            String[] userAndFriends = value.toString().split(":");
            //第二次通过“,”把切分开来
            String[] friends = userAndFriends[1].split(",");
            v.set(userAndFriends[0]);
            for (String f :friends
                    ) {
                k.set(f);
                context.write(k,v);
            }
        }
    }
    public static class CommonFriendsOneReducer extends Reducer<Text,Text,Text,Text>{
        Text k= new Text();

        @Override
        protected void reduce(Text friend, Iterable<Text> users, Context context) throws IOException, InterruptedException {
            ArrayList<String> userList = new ArrayList<String>();
            for (Text user:users
                 ) {
                userList.add(user.toString());
            }
            Collections.sort(userList);
            for(int i=0;i<userList.size()-1;i++){
                for(int j=i+1;j<userList.size();j++){
                    k.set(userList.get(i)+"--"+userList.get(j));
                    context.write(k,friend);
                }
            }
        }
    }
    public static void main(String [] arge) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);


        job.setJarByClass(CommonFriendsOne.class);
        job.setMapperClass(CommonFriendsOneMapper.class);
        job.setReducerClass(CommonFriendsOneReducer.class);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job,new Path("f:/friends/input"));
        FileOutputFormat.setOutputPath(job,new Path("f:/friends/output"));
        job.waitForCompletion(true);
    }
}
