package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring.objects.User;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService {

    private final HashMap<String, User> userRepository;

    @Autowired
    public UserService(@Qualifier("userRepository") HashMap<String, User> userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> loadUser(String username) {
        return Optional.ofNullable(userRepository.getOrDefault(username, null));
    }


    void signUpUser(User user) {
        userRepository.put(user.getUsername(), user);
    }
}
