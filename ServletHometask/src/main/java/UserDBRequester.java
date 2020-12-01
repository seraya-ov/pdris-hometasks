import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDBRequester {

    private final Connection connection;
    private final ArrayList<PreparedStatement> preparedStatements;

    public UserDBRequester(ServletContext context) {
        connection = (Connection) context.getAttribute("DBConnection");
        preparedStatements = new ArrayList<>();
    }

    private ResultSet checkForUser(String username) throws SQLException {
        PreparedStatement checkPs = this.connection.prepareStatement("select * from userdb.users where username=? limit 1");
        checkPs.setString(1, username);
        this.preparedStatements.add(checkPs);

        return checkPs.executeQuery();
    }

    public boolean userExists(String username) throws SQLException {
        ResultSet rs = checkForUser(username);
        return (rs != null && rs.next());
    }

    public String getPassword(String username) throws SQLException {
        ResultSet rs = checkForUser(username);
        if (rs != null && rs.next()) {
            return rs.getString("password");
        }
        return "";
    }

    public void addUser(String username, String password) throws SQLException {
        PreparedStatement insertPs = this.connection.prepareStatement("insert into userdb.users(username, password) values (?,?)");
        insertPs.setString(1, username);
        insertPs.setString(2, password);
        insertPs.execute();
        insertPs.close();
    }

    public void close() throws SQLException {
        for (PreparedStatement st: this.preparedStatements) {
            st.close();
        }
    }
}
