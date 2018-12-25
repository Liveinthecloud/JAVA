package TEST;

import java.sql.*;



public class Main {



    public static void main(String[] args) {
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
        String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=test";//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
        String Name="sa";
        String Pwd="123456789";

        Statement sql;
        ResultSet rs;
        try
        {
            Class.forName(driverName);
            Connection conn=DriverManager.getConnection(dbURL,Name,Pwd);
            System.out.println("连接数据库成功");
            sql=conn.createStatement();
            rs=sql.executeQuery("select * from Sheet1$");
           while (rs.next()){
               System.out.print(rs.getString(1)+"      ");
               System.out.print(rs.getString(2)+"      ");
               System.out.print(rs.getString(3)+"      ");
               System.out.print(rs.getString(4)+"      ");
               System.out.print(rs.getString(5)+"      ");
               System.out.print(rs.getString(6)+"      ");
               System.out.println();
           }



        }catch(Exception e){
            e.printStackTrace();
            System.out.println("连接失败");
        }
    }

}