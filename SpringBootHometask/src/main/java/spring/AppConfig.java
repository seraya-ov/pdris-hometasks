package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.objects.User;

import java.util.HashMap;

@Configuration
public class AppConfig {

    @Bean
    HashMap<String, User> userRepository() {
        return new HashMap<>();
    }

    @Bean
    HashMap<String, String> authAudit() {
        return new HashMap<>();
    }
}
