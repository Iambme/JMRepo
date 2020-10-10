package app.controller;

import app.model.MyUser;
import app.model.Role;
import app.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private MyUserService myUserService;

    @Autowired
    public UserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping("/login")
    public String loginPage() {
        myUserService.saveMyUser(new MyUser("Sherlock", "Holmes", "sh@bk.ru", 33, "admin"), new Role("ROLE_ADMIN"));
        myUserService.saveMyUser(new MyUser("Doctor", "Watson", "wat@bk.ru", 40, "user"), new Role("ROLE_USER"));
        return "login";
    }

    @GetMapping("/user/{id}")
    public String user(@PathVariable int id, Model model) {
        MyUser myUser = myUserService.getMyUser(id);
        model.addAttribute("myUser", myUser);
        return "user";
    }
}
