package classes.myservlet.control;
import classes.mybean.data.DataByPage;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.String;

/**
 * Created by jh on 2017/5/19.
 */
public class UsersDelete  extends HttpServlet{
    public void init(ServletConfig config)throws ServletException {
        super.init(config);
        try {
            Class.forName("com.mysql.jdbc.Driver");// 加载驱动程序以连接数据库
        } catch (Exception e) {}
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        String Name =request.getParameter("Name");
        HttpSession session= request.getSession(true);
        Connection con =null;
        String url = "jdbc:mysql://localhost:3306/webdata?characterEncoding=UTF-8";
        String username = "root";
        String SQLpassword = "123123";
        try{
            con = DriverManager.getConnection( url ,username, SQLpassword);

            Statement sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            sql.executeUpdate("DELETE  FROM member where logname='"+Name+"'");
            //ResultSet rs=sql.executeQuery("DELETE  FROM member where logname="+Name);
            con.close();
        }
        catch(SQLException e){
            System.out.print(e);
        }
        response.sendRedirect("userinfo.jsp");//重定向
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        doPost(request, response);
    }
}
