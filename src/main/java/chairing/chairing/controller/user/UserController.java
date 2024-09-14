package chairing.chairing.controller.user;

import jakarta.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;


import chairing.chairing.dto.user.UserCreateRequest;

import chairing.chairing.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("userCreateRequest", new UserCreateRequest());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute @Valid UserCreateRequest userCreateRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        // 회원가입 로직
        try {
            userService.create(userCreateRequest);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup";
        } catch (Exception e) {
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup";
        }
    
        return "redirect:/user/login";
    }
    

        // 로그인 페이지 반환
        @GetMapping("/login")
        public String login() {
            return "login";  // login.html을 반환
        }
}