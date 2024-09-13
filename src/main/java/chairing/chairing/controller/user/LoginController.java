package chairing.chairing.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/user/login")
    public String login() {
        return "login";  // login.html을 반환
    }
}