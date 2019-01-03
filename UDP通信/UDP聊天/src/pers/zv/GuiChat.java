package pers.zv;

/**
 * @date 2018/9/28 0028-上午 8:24
 */
        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.MouseAdapter;
        import java.awt.event.MouseEvent;
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
    private  JButton nextBT;                    //在加个聊天窗口
    private DatagramSocket datagramSocket;     //功能实现

    //构造函数
    public GuiChat(){
        setUpUI();
        initSocket();
        setListener();
    }
    //实现用户窗口
    private void setUpUI(){
        //窗口初始化
        this.setTitle("聊天");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(550,400);
        this.setResizable(true);       //窗口大小不可调
        this.setLocationRelativeTo(null);//居中窗口
        //窗口的上半部分
        stateLB=new JLabel("当前还未启动监听");
        stateLB.setHorizontalAlignment(JLabel.RIGHT);
        //窗口中间聊天记录部分
        centerTextArea=new JTextArea();         //聊天显示区域
        centerTextArea.setEnabled(false);       //不可改
        centerTextArea.setFont(new Font("宋体", 1, 10));//显示字体
        centerTextArea.setBackground(new Color(255,255,255));
        //窗口底部部分
        southPanel=new JPanel(new BorderLayout());          //设置布局模式
        inputTextArea=new JTextArea(5,20);      //输入区域
        inputTextArea.setBackground(new Color(255,255,255));
        bottomPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));//控件之间的距离和距离上下的距离
        ipTextField=new JTextField("127.0.0.1",10);//默认pi地址
        ipTextField.setBackground(new Color(0,191,255));
        remotePortTF=new JTextField(String.valueOf(DEFAULT_PORT),8);
        remotePortTF.setBackground(new Color(0,191,255));
        ImageIcon icon=new ImageIcon("image\\send.png");
        sendBT=new JButton(icon);
        sendBT.setContentAreaFilled(false);  //对JButton透明的设置
        sendBT.setBorderPainted(false);       //对JButton去掉按钮的边框的设置
        sendBT.setBorder(BorderFactory.createRaisedSoftBevelBorder());//凸起来的按钮
        //sendBT.setFont(new Font("华文行楷", 1, 15));
        sendBT.setBackground(new Color(0,191,255));


        ImageIcon icon1=new ImageIcon("image\\q.png");
        clearBT=new JButton(icon1);
        clearBT.setContentAreaFilled(false);
        clearBT.setBorderPainted(false);

        nextBT=new JButton("New one");
        bottomPanel.add(ipTextField);
        bottomPanel.add(remotePortTF);
        bottomPanel.add(sendBT);
        bottomPanel.add(clearBT);
        bottomPanel.add(nextBT);
        southPanel.add(new JScrollPane(inputTextArea),BorderLayout.CENTER);//输入框滑条模式
        southPanel.add(bottomPanel,BorderLayout.SOUTH);
        //添加窗口的部分的组件
        this.add(stateLB,BorderLayout.NORTH);
        this.add(new JScrollPane(centerTextArea),BorderLayout.CENTER);
        this.add(southPanel,BorderLayout.SOUTH);
        this.setVisible(true);
    }
    //设置监听事件
    private void setListener(){
        //为sendBT按钮添加事件监听器
        sendBT.addMouseListener(new MouseAdapter() {
            @Override
            //进入发送按键调用send1.png
            public void mouseEntered(MouseEvent e) { sendBT.setIcon(new ImageIcon("image\\send1.png")); }

            @Override
            //退出发送按键调用send.png
            public void mouseExited(MouseEvent e) {
                sendBT.setIcon(new ImageIcon("image\\send.png"));
            }
        });
        sendBT.addActionListener(new ActionListener() {
            @Override
            //单机发送按键
            public void actionPerformed(ActionEvent e) {

                final String ipAddress=ipTextField.getText();//获取发送的目标IP
                final String remotePort=remotePortTF.getText();//获取发送的目标端号

                //判断IP和Port是否为空
                if(ipAddress==null||ipAddress.trim().equals("")||           //trim():去掉字符串首尾的空格。
                        remotePort==null||remotePort.trim().equals("")){
                    JOptionPane.showMessageDialog(GuiChat.this,"请输入IP地址和端口号");//ip或端口错误弹出提醒框
                    return;
                }
                if(datagramSocket==null||datagramSocket.isClosed()){
                    JOptionPane.showMessageDialog(GuiChat.this,"监听不成功");
                    return;
                }
                String sendContent=inputTextArea.getText(); //获取需要发送的消息
                //如果发送消息为空，弹出提醒
                if(sendContent.equals("")){
                    JOptionPane.showMessageDialog(GuiChat.this,"发送信息为空");
                    return;
                }
                byte [] bufferSendMessage=sendContent.getBytes();//转换成字节数组
                try{
                    //将发送的内容显示在自己的聊天记录
                    centerTextArea.append("我对"+ipAddress+":"+remotePort+"说:\n"+
                            inputTextArea.getText()+"\n\n");
                    //添加内容后，使滚动条自动滚动到最底部
                    centerTextArea.setCaretPosition(centerTextArea.getText().length());
                    //发送  创建一个数据包，添加数据到指定Ip的指定端口
                    datagramSocket.send(new DatagramPacket(bufferSendMessage,bufferSendMessage.length, InetAddress.getByName(ipAddress),Integer
                            .parseInt(remotePort)));
                    inputTextArea.setText("");      //聊天框重置为空
                }catch (IOException e1){
                    JOptionPane.showMessageDialog(GuiChat.this,"错误，发送不成功！");
                    e1.printStackTrace();
                }
            }
        });
        //为clearBT按键添加事件
        clearBT.addMouseListener(new MouseAdapter() {
            @Override
            //进人清除按键
            public void mouseEntered(MouseEvent e) { clearBT.setIcon(new ImageIcon("image\\q1.png")); }
            //退出清除按键
            @Override
            public void mouseExited(MouseEvent e) { clearBT.setIcon(new ImageIcon("image\\q.png")); }
        });
        //单击事件
        clearBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerTextArea.setText("");
            }//清除聊天记录框
        });
        //添加一个新的对话框
        nextBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiChat();
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
                    port=Integer.parseInt(JOptionPane.showInputDialog(this,"请输入端口号",//获取输入的监听端口
                            "端口号",JOptionPane.QUESTION_MESSAGE));
                    if(port<1||port>65535)
                        throw new RuntimeException("端口号超出范围");

                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,"你输入的端口不正确，请输入1~65535之间");
                    continue;       //如果输入监听端口错误，在从while进入
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
    //开启一个线程接收发送来的信息
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
                        centerTextArea.setCaretPosition(centerTextArea.getText().length());//向下滚动
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
