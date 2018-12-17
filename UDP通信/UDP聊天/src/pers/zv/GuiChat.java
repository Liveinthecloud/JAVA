package pers.zv;

/**
 * @author zengwei
 * @date 2018/9/28 0028-上午 8:24
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class GuiChat extends JFrame{
    private static int DEFAULT_PORT=8888;
    private JLabel stateLB;                     //显示监听状态
    private JTextArea centerTextArea;           //显示聊天记录
    private JPanel southPanel;                  //最下面的面板
    private JTextField ipTextField;             //IP输入框
    private JTextArea inputTextArea;            //聊天输入框
    private JTextField remotePortTF;            //端口输入框
    private JPanel bottomPanel;                 //放置IP输入框，按钮等
    private JButton sendBT;                     //发送按钮
    private JButton clearBT;                    //清除聊天记录按钮
    private DatagramSocket datagramSocket;      //功能实现

    //构造函数
    public GuiChat(){
        setUpUI();
        initSocket();
        setListener();
    }
    //实现用户窗口
    private void setUpUI(){
        //窗口初始化
        this.setTitle("GUI聊天");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,400);
        this.setResizable(false);       //窗口大小不可调
        this.setLocationRelativeTo(null);//居中窗口
        //窗口的上半部分
        stateLB=new JLabel("当前还未启动监听");
        stateLB.setHorizontalAlignment(JLabel.RIGHT);
        //窗口中间聊天记录部分
        centerTextArea=new JTextArea();         //聊天显示区域
        centerTextArea.setEnabled(false);       //不可改
        centerTextArea.setBackground(new Color(255,106,106));
        //窗口底部部分
        southPanel=new JPanel(new BorderLayout());
        inputTextArea=new JTextArea(5,20);      //输入区域
        inputTextArea.setBackground(new Color(255,106,106));
        bottomPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
        ipTextField=new JTextField("127.0.0.1",10);
        remotePortTF=new JTextField(String.valueOf(DEFAULT_PORT),8);
        sendBT=new JButton("发送");
        clearBT=new JButton("清屏");
        bottomPanel.add(ipTextField);
        bottomPanel.add(remotePortTF);
        bottomPanel.add(sendBT);
        bottomPanel.add(clearBT);
        southPanel.add(new JScrollPane(inputTextArea),BorderLayout.CENTER);
        southPanel.add(bottomPanel,BorderLayout.SOUTH);
        //添加窗口的部分的组件
        this.add(stateLB,BorderLayout.NORTH);
        this.add(new JScrollPane(centerTextArea),BorderLayout.CENTER);
        this.add(southPanel,BorderLayout.SOUTH);
        this.setVisible(true);
    }
    private void setListener(){
        //为sendBT按钮添加事件监听器
        sendBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取发送的目标IP和端号
                final String ipAddress=ipTextField.getText();
                final String remotePort=remotePortTF.getText();
                //判断IP和Port是否为空
                if(ipAddress==null||ipAddress.trim().equals("")||           //trim():去掉字符串首尾的空格。
                remotePort==null||remotePort.trim().equals("")){
                    JOptionPane.showMessageDialog(GuiChat.this,"请输入IP地址和端口号");
                    return;
                }
                if(datagramSocket==null||datagramSocket.isClosed()){
                    JOptionPane.showMessageDialog(GuiChat.this,"监听不成功");
                    return;
                }
                //获取需要发送的消息
                String sendContent=inputTextArea.getText();
                byte [] buffer=sendContent.getBytes();
                try{
                    //将发送的内容显示在自己的聊天记录
                    centerTextArea.append("我对"+ipAddress+":"+remotePort+"说:\n"+
                            inputTextArea.getText()+"\n\n");
                    //添加内容后，使滚动条自动滚动到最底部
                    centerTextArea.setCaretPosition(centerTextArea.getText().length());
                    //发送
                    datagramSocket.send(new DatagramPacket(buffer,buffer.length, InetAddress.getByName(ipAddress),Integer
                    .parseInt(remotePort)));
                    inputTextArea.setText("");
                }catch (IOException e1){
                    JOptionPane.showMessageDialog(GuiChat.this,"错误，发送不成功！");
                    e1.printStackTrace();
                }
            }
        });
        //为clearBT按键添加事件
        clearBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerTextArea.setText("");
            }
        });
    }
    //DatagramSocket的监听
    private void initSocket(){
        int port=DEFAULT_PORT;          //端口号
        while (true){
            try{
                if(datagramSocket!=null&&!datagramSocket.isClosed())
                    datagramSocket.close();
                try{//判断端口号是否在1~65535
                    port=Integer.parseInt(JOptionPane.showInputDialog(this,"请输入端口号",
                            "端口号",JOptionPane.QUESTION_MESSAGE));
                    if(port<1||port>65535)
                        throw new RuntimeException("端口号超出范围");

                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,"你输入的端口不正确，请输入1~65535之间");
                    continue;
                }
                //启动DatagramSocket
                datagramSocket=new DatagramSocket(port);
                //调用startListen方法
                startListen();
                stateLB.setText("已在"+port+"端口监听");
                break;
            }catch (SocketException e){
                JOptionPane.showMessageDialog(this,"端口已被占用，请重新设置端口");
                stateLB.setText("当前还未启动监听");
            }
        }
    }
    //startListen
    private void startListen(){
        new Thread(){
            private DatagramPacket p;
            public void run(){
                byte [] beffer=new byte[1024];
                //创建DatagramPacket
                p=new DatagramPacket(beffer,beffer.length);
                while (!datagramSocket.isClosed()){
                    try{
                        datagramSocket.receive(p);      //接收聊天信息
                        //添加到聊天记录
                        centerTextArea.append(p.getAddress().getHostAddress()+
                                ":"+((InetSocketAddress)p.getSocketAddress()).getPort()+
                                "对我说：\n"+new String(p.getData(),0,p.getLength())+"\n\n");
                        centerTextArea.setCaretPosition(centerTextArea.getText().length());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    public static void main(String[] Q_Q){
        GuiChat guiChat=new GuiChat();
    }
}
