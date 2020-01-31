package test;

import java.sql.*;

public class JDBC {
	//test为数据库名称
    // MySQL 8.0 以下版本选择
    //static final String JdbcDriver = "com.mysql.jdbc.Driver";  
    //static final String Url = "jdbc:mysql://localhost:3306/test";
 
    // MySQL 8.0 以上版本选择
    static final String JdbcDriver = "com.mysql.cj.jdbc.Driver";  
    static final String Url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
 
 
    //输入连接数据库的用户名与密码
    static final String User = "root";//输入你的数据库库名
    static final String PassWord = "123456";//输入你的数据库连接密码
 
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JdbcDriver);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(Url,User,PassWord);
        
            // 执行查询
            System.out.println("输入sql语句后并执行...");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from user";// 这里填写需要的sql语句
            ResultSet rs = stmt.executeQuery(sql);
        
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");//获取id值
                String name = rs.getString("user_name");//获取user_name值
                String sex = rs.getString("sex");//获取sex值
    
                // 输出数据
                System.out.print("\nid: " + id);
                System.out.print("\n名字: " + name);
                System.out.print("\n性别: " + sex);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("\n执行成功！");
    }
}