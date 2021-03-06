package pers.zengwei.UdpChat.Button;

import javax.swing.*;
import java.awt.*;

/**
 * 1、对JButton大小的设置
 * 因为JButen是属于小器件类型的，所以一般的setSize不能对其惊醒大小的设置，所以一般我们用
 * button.setPreferredSize(new Dimension(30,30)); //（30，30） 是你要设置按钮的大小
 *
 * 2、对JButton透明的设置
 * 按钮设置为透明，这样就不会挡着后面的背景
 * button.setContentAreaFilled(false);
 *
 * 3、对JButton去掉按钮的边框的设置
 * 如果有时候你的按钮不需要边框因为边框影响美观或者是因为你需要的是点击之前按钮呈现 普通图标形式，点击之后才有各种效果的话就可以用这种方法去掉边框
 * button.setBorderPainted(false);
 *
 * 4、对JButton添加图标呢的设置 // 实例化一个图标对象
 * ImageIcon image = new ImageIcon(icons[i]); // 实例化按钮对象，并且设置按钮上显示图片
 * JButton button = new JButton(image); ——或者
 * button.setIcon(new ImageIcon(getClass().getResource("qq.png"))); //qq.png是你要添加的图片
 *
 * 5、让按钮随按钮上的图案变化 butten.setMargin(new Insets(0,0,0,0));
 *
 * 6、设置凸起来的按钮，很多其他的swing也可用此方法
 * butten.setBorder(BorderFactory.createRaisedBevelBorder());
 *
 * 7、设置凹起来的按钮，很多其他的swing也可用此方法
 * button.setBorder(BorderFactory.createLoweredBevelBorder());
 *
 * 8、设置按钮的前景色和背景色
 * button .setFont(new java.awt.Font("华文行楷", 1, 15));
 * button.setBackground(Color.green);
 *
 * 9、改变按钮的样式
 * UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
 */
public class MyButton extends JButton {
    private ImageIcon icon;
    public MyButton(){
        super();
    }
    public MyButton(String filename){
        super();
        String imageName="image\\"+filename;
        icon =new ImageIcon(imageName);
        this.setIcon(icon);         //设置图片背景
        this.setBorderPainted(false);//去掉Button的边框
        this.setContentAreaFilled(false);//透明
        this.setPreferredSize(new Dimension(60,60));//设置尺寸
        this.setBorder(BorderFactory.createRaisedBevelBorder());//设置凸起来的按钮
    }
}
