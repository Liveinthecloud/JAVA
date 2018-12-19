package pers.zengwei.UdpChat.GUI;

import pers.zengwei.UdpChat.Button.MyButton;

import javax.swing.*;
import java.awt.*;

public class SetGUI {

    public SetGUI(){
        setGui();
    }
    public void setGui(){
        JFrame jFrame=new JFrame("😀");
        JPanel jPanel=new JPanel();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500,400);
        jFrame.setResizable(true);
        jFrame.setLocationRelativeTo(null);
        jPanel.add(new MyButton("2.png"));
        jFrame.add(jPanel);

        jFrame.setVisible(true);
    }

    public static void main(String[] arge){
        new SetGUI();
    }
}
