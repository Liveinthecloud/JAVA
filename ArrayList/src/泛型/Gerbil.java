package 泛型;

import java.util.ArrayList;

public class Gerbil {
    private int gerbilNumber;
    public Gerbil(int gerbilNumber){
        this.gerbilNumber=gerbilNumber;
    }
    public void hop(){
        System.out.println("沙鼠"+gerbilNumber+"正在跳跃！");
    }
    public static void main(String[]arge){
        ArrayList<Gerbil> gerbils=new ArrayList<Gerbil>();
        for(int i=0;i<4;i++){
            gerbils.add(new Gerbil(i));
        }
        for(Gerbil temp:gerbils){
            temp.hop();
        }
    }
}
