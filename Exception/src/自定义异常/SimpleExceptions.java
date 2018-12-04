package 自定义异常;

public class SimpleExceptions extends Exception {
    public void  exception(){
        System.out.println("抛出异常");
    }
}
class InheritingExceptions{
    public void test(boolean b)throws SimpleExceptions{
        System.out.println("Throw SimpleException for test()");
        if(b){
            throw new SimpleExceptions();//抛出异常
        }
    }
    public static void main(String[] arge){
        InheritingExceptions i=new InheritingExceptions();
        try{

            i.test(true);

        }catch(SimpleExceptions e){//监测异常
            e.exception();
            e.printStackTrace();
        }
    }
}
