package 恢复模型;
/*
异常处理程序的工作是修正错误
 */
public class Recover {
    public static void main(String[] arge) throws Exception {
        int i=10;
        while (true){
            try{
                if(i>0){
                    throw new Exception();
                }
                System.out.println("退出while循环");
                break;
            }catch(Exception e){
                i--;
                System.out.println("修正：i="+i);
            }
        }
        System.out.println("修改正完成");
    }
}
