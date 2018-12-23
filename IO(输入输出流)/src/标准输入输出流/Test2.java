package 标准输入输出流;

import 缓冲区.Buffer;

import java.io.*;

public class Test2 {
    public static void main(String[]arge) throws IOException {
        StringBuffer sb=new StringBuffer();
        int ch;
        while ((ch=System.in.read())!=-1){
            if(ch=='\n'||ch=='\r'){
                break;
            }
            sb.append((char)ch);
        }
        System.out.println(sb);
        //标准输入输出重定向
        System.setIn( new FileInputStream("Resources\\TXT\\test1.txt"));
        System.setOut( new PrintStream("Resources\\TXT\\重定向输出.txt"));
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String ln;
        while ((ln=br.readLine())!=null){
            System.out.println(ln);
        }
    }
}
