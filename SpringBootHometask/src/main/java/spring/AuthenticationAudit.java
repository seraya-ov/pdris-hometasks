package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


@Service
public class AuthenticationAudit {

    private final HashMap<Long, String> authenticationAudit;

    @Autowired
    public AuthenticationAudit(@Qualifier("authAudit") HashMap<Long, String> authenticationAudit) {
        this.authenticationAudit = authenticationAudit;
    }

    public void addInfo(String username, String authLog) {
        authenticationAudit.put(System.currentTimeMillis(), username + ": " + authLog);
    }

    public HashMap<Long, String> getAuthenticationAudit() {
        return authenticationAudit;
    }
}
