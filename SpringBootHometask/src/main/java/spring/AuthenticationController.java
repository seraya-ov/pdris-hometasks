package spring;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.objects.User;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationAudit authenticationAudit;

    public AuthenticationController(UserService userService, AuthenticationAudit authenticationAudit) {
        this.userService = userService;
        this.authenticationAudit = authenticationAudit;
    }

    @GetMapping("/audit")
    public String getAuthenticationAuditLog() {
        StringBuilder log = new StringBuilder(
                "<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\" lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Audit Page</title>\n" +
                "</head>\n" +
                "<body>");
        HashMap<Long, String> data = authenticationAudit.getAuthenticationAudit();
        Object[] keys = data.keySet().toArray();
        Arrays.sort(keys);
        for (Object key : keys) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date((Long)key);
            log.append("<div>").append(formatter.format(date)).append(": ").append(data.get(key)).append("</div>");
        }
        log.append(
                "</body>\n" +
                "</html>");
        return log.toString();
    }

    @GetMapping({"/", "login"})
    ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("register")
    ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("register")
    ModelAndView register(@ModelAttribute User user, RedirectAttributes redirectAttrs) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> optionalUser = userService.loadUser(user.getUsername());

        String errorMsg = user.checkUserDataForSignUp(optionalUser);

        if (!errorMsg.equals("")) {
            modelAndView.setViewName("redirect:register");
        } else {
            userService.signUpUser(user);
            modelAndView.setViewName("redirect:login");
        }
        redirectAttrs.addAttribute("error", errorMsg);
        redirectAttrs.addAttribute("success", "Registration successful, please login below.");
        return modelAndView;
    }

    @PostMapping("login")
    ModelAndView login(@ModelAttribute User user, RedirectAttributes redirectAttrs) {
        Optional<User> optionalUser = userService.loadUser(user.getUsername());
        ModelAndView modelAndView = new ModelAndView();

        String errorMsg = user.checkUserDataForSignIn(optionalUser, authenticationAudit);

        if (!errorMsg.equals("")) {
            modelAndView.setViewName("redirect:login");
        } else {
            modelAndView.setViewName("redirect:home");
            redirectAttrs.addFlashAttribute("name", user.getUsername());
            authenticationAudit.addInfo(user.getUsername(), "Authentication succeeded.");
        }

        redirectAttrs.addAttribute("error", errorMsg);
        return modelAndView;
    }

    @GetMapping("home")
    public ModelAndView home(@ModelAttribute("name") Object flashAttribute) {
        ModelAndView modelAndView = new ModelAndView();
        if (flashAttribute.getClass() != String.class) {
            modelAndView.setViewName("redirect:login");
        } else {
            modelAndView.setViewName("home");
            modelAndView.addObject("name", flashAttribute);
        }
        return modelAndView;
    }

}
