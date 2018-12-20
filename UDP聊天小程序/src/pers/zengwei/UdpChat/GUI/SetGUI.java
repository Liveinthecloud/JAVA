package pers.zengwei.UdpChat.GUI;

import pers.zengwei.UdpChat.Button.MyButton;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SetGUI {

    public SetGUI(){
        setGui();
    }
    public void setGui(){
        JFrame jFrame=new JFrame("😀");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//直接关闭窗口
        jFrame.setSize(400,800);
        jFrame.setLayout(new FlowLayout());//布局模式

        JPanel jPanel=new JPanel();
        //jPanel.setBackground(new Color(110,12,11));

        MyButton button1= new MyButton("1.jpg");
        button1.setPreferredSize(new Dimension(400,800));//调节按键的大小
        jPanel.add(button1);
        button1.addMouseListener(new MouseAdapter() {
            int i = 0;
            @Override
            public void mouseClicked(MouseEvent e) {


                if (i == 0) {

                    button1.setIcon(new ImageIcon("image\\2.jpg"));
                    i = 1;

                } else {
                    button1.setIcon(new ImageIcon("image\\1.jpg"));
                    i = 0;


                }
            }
        });
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(jPanel);

        jFrame.setVisible(true);
    }

    public static void main(String[] arge){
        new SetGUI();
    }
}
