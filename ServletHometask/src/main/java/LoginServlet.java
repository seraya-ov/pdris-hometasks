import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.User;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errorMsg = checkRequestParameters(username, password);

        if (!errorMsg.equals("")) {
            formResponse(request, response,
                    "<font color=red>" + errorMsg + "</font>"
            );
        } else {
            UserDBRequester userDBRequester = new UserDBRequester(getServletContext());
            try {
                if (userDBRequester.userExists(username)) {
                    if (!userDBRequester.getPassword(username).equals(password)) {
                        formResponse(request, response,
                                "<font color=red>Wrong password!</font>"
                        );
                    } else {
                        User user = new User(username);
                        HttpSession session = request.getSession();
                        session.setAttribute("User", user);
                        response.sendRedirect("home.jsp");
                    }
                } else {
                    formResponse(request, response,
                            "<font color=red>User " + username + " doesn't exist. Please, sign up to proceed.</font>"
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("DB Connection problem.");
            } finally {
                try {
                    userDBRequester.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ServletException("DB Connection problem.");
                }
            }
        }
    }

    private void formResponse(HttpServletRequest request, HttpServletResponse response, String msg) throws IOException, ServletException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
        PrintWriter out = response.getWriter();
        out.println(msg);
        rd.include(request, response);
    }

    private String checkRequestParameters(String username, String password) {
        String errorMsg = "";
        if (username == null || username.equals("")) {
            errorMsg += "Username can't be null or empty" + username + "\n";
        }
        if (password == null || password.equals("")) {
            errorMsg += "Password can't be null or empty" + password;
        }
        return errorMsg;
    }

}