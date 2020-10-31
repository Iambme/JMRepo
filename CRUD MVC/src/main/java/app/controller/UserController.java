package app.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = {"/login", "/"})
    public String loginPage() {
        userService.saveMyUser(new User("Sherlock", "Holmes", "sh@bk.ru", 33, "admin"), "ROLE_ADMIN");
        userService.saveMyUser(new User("Doctor", "Watson", "wat@bk.ru", 40, "user"), "ROLE_USER");
        return "login";
    }

    @GetMapping("admin")
    public String listUsers(Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("listUsers", userService.getMyUserList());
        model.addAttribute("userEdit", new User());
        return "admin";
    }

    @GetMapping(value = "user")
    public String userPage(Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getPrincipal());
        return "user";
    }
}
