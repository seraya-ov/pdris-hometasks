package spring.objects;

import spring.AuthenticationAudit;

import java.util.Optional;

public class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String checkUserDataForSignIn(Optional<User> optionalUser, AuthenticationAudit authenticationAudit) {
        if (username == null || username.equals("")) {
            return "Username can't be null or empty!";
        }
        if (password == null || password.equals("")) {
            return "Password can't be null or empty!";
        }
        if (!optionalUser.isPresent()) {
            authenticationAudit.addInfo(username, "Authentication failed: user doesn't exist.");
            return "User " + username + " doesn't exist. Please, sign up to proceed.";
        } else {
            User realUser = optionalUser.get();
            if (!realUser.getPassword().equals(password)) {
                authenticationAudit.addInfo(username, "Authentication failed: wrong password.");
                return "Wrong password!";
            }
        }
        return "";
    }

    public String checkUserDataForSignUp(Optional<User> optionalUser) {
        if (username == null || username.equals("")) {
            return "Username can't be null or empty!";
        }
        if (password == null || password.equals("")) {
            return "Password can't be null or empty!";
        }
        if (username.equals("admin")) {
            return "You can not register as admin!";
        }
        if (optionalUser.isPresent()) {
            return "User " + username + " already exists!";
        }
        return "";
    }
}