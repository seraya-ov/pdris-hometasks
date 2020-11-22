import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Register", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errorMsg = checkRequestParameters(username, password);

        if (!errorMsg.equals("")) {
            formResponse(request, response,
                    "<font color=red>" + errorMsg + "</font>",
                    "/register.html");
        } else {
            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            PreparedStatement insert_ps = null;
            PreparedStatement check_ps = null;
            try {
                check_ps = con.prepareStatement("select * from userdb.users where username=?");
                check_ps.setString(1, username);
                ResultSet rs = check_ps.executeQuery();

                if (rs != null && rs.next()) {
                    formResponse(request, response,
                            "<font color=red>User " + username + " already exists!</font>",
                            "/register.html");
                } else {
                    insert_ps = con.prepareStatement("insert into userdb.users(username, password) values (?,?)");
                    insert_ps.setString(1, username);
                    insert_ps.setString(2, password);

                    insert_ps.execute();

                    formResponse(request, response,
                            "<font color=green>Registration successful, please login below.</font>",
                            "/login.html");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("DB Connection problem.");
            } finally {
                try {
                    if (check_ps != null) {
                        check_ps.close();
                    }
                    if (insert_ps != null) {
                        insert_ps.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void formResponse(HttpServletRequest request, HttpServletResponse response, String msg, String url) throws IOException, ServletException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        PrintWriter out = response.getWriter();
        out.println(msg);
        rd.include(request, response);
    }

    private String checkRequestParameters(String username, String password) {
        String errorMsg = "";

        if (password == null || password.equals("")) {
            errorMsg = "Password can't be null or empty!";
        }
        if (username == null || username.equals("")) {
            errorMsg = "Name can't be null or empty!";
        }
        if (username != null && username.equals("admin")) {
            errorMsg = "You can't register as admin!";
        }
        return errorMsg;
    }

}
