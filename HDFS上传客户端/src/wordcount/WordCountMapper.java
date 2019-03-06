package wordcount;

public class WordCountMapper implements Mapper {

    @Override
    public void map(String line, Context context) {
        //空格切割源数据
        String[] words = line.split(" ");
        for (String word : words
                ) {
            //查看HashMap中是否已存在该word
            Object value = context.get(word);
            if (value==null){
                context.write(word,1);
            }else {
               int v= (int )value;
                context.write(word,v+1);
            }
        }
    }
}
