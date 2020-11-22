package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationAudit {

    private final HashMap<String, String> authenticationAudit;

    @Autowired
    public AuthenticationAudit(@Qualifier("authAudit") HashMap<String, String> authenticationAudit) {
        this.authenticationAudit = authenticationAudit;
    }

    public void addInfo(String username, String authLog) {
        authenticationAudit.put(username, authLog);
    }

    public HashMap<String, String> getAuthenticationAudit() {
        return authenticationAudit;
    }
}
