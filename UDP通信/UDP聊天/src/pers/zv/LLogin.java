package pers.zv;



import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

public class LLogin {
    public static void main(String[] arge){
        new loginFrame();
    }
}

class loginFrame extends JFrame implements ActionListener{
    Box box1,box2,baseBox;
    JLabel userName,userPwd,tubiao;
    JTextField nameField;
    JPasswordField pwdField;
    JButton button;
    JTabbedPane choose;
    JPanel panel1,panel2;
    loginFrame(){
        setBackground(Color.orange);
        tubiao=new JLabel(new ImageIcon("image\\1.png "));  //图片在原有基础上要调整大小
        //tubiao.setBackground(new Color(140,255,251));
        this.add(tubiao,BorderLayout.NORTH);
        userName=new JLabel("账号",JLabel.CENTER);
        userPwd=new JLabel("密码",JLabel.CENTER);
        nameField=new JTextField(8);
        pwdField=new JPasswordField(8);
        panel1=new JPanel();
        panel2=new JPanel();
        choose=new JTabbedPane();
        choose.add("登录",panel1);
        panel1.setBackground(new Color(140,255,251));
        panel1.setLayout(new GridLayout(2,2));
        panel1.add(userName);panel1.add(nameField);
        panel1.add(userPwd);panel1.add(pwdField);
        add(choose,BorderLayout.CENTER);
        button=new JButton("登陆");
        add(button,BorderLayout.SOUTH);
        button.addActionListener(this);
        //小图标
        ImageIcon tubiao=new ImageIcon("图片路径");
        setIconImage(tubiao.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(400,150,550,400);
        setTitle("聊天小程序");
        validate();
    }


    public void actionPerformed(ActionEvent e){
        String name,pwd;
        name=nameField.getText();
        pwd=pwdField.getText();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //加载数据库驱动

        }
        catch(ClassNotFoundException ex){
            System.out.println(ex);
        }
        try{
            Connection con;
            Statement sql;
            ResultSet rs;
            String url,userName,userPwd;
            // 连接数据库的语句
            url="jdbc:sqlserver://127.0.0.1;DatabaseName=Student";

            userName="sa";
            userPwd="123456789";
            con=DriverManager.getConnection(url,userName,userPwd);//尝试建立与给定数据库URL的连接。 抛出SQLException
            sql=con.createStatement();
            rs=sql.executeQuery("select * from login where name ='"+name+"' and pwd='"+pwd+"'");//SQL语句：对应自己数据库建的表填写
            boolean key=false;
            while(rs.next()){
                key=true;
            }
            if(key){
                JOptionPane.showMessageDialog(this, "登陆成功！","消息对话框",JOptionPane.WARNING_MESSAGE);
                this.dispose();
                new GuiChat();

            }
            else
                JOptionPane.showMessageDialog(this, "账号或者密码错误!","消息对话框",JOptionPane.WARNING_MESSAGE);
        }
        catch(SQLException exp){
            System.out.println(exp);
        }

    }
}
