import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

        String dbURL = ctx.getInitParameter("dbURL");
        String user = ctx.getInitParameter("dbUser");
        String pwd = ctx.getInitParameter("dbPassword");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, user, pwd);
            ctx.setAttribute("DBConnection", connection);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("DB Connection problem.");
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Connection con = (Connection) servletContextEvent.getServletContext().getAttribute("DBConnection");
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("DB Connection problem.");
            }
        }
    }

}
