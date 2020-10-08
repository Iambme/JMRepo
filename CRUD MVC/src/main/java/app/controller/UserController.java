package app.controller;

import app.model.MyUser;
import app.model.Role;
import app.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private MyUserService myUserService;

    @Autowired
    public void setMyUserService(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String startPage() {
        return "index";
    }

    @GetMapping("/allUser")
    public String allUser(Model model) {
        List<MyUser> myUserList = myUserService.getMyUserList();
        model.addAttribute("allUser", myUserList);
        return "allUser";
    }

    @GetMapping("/addUser")
    public String addUser_index(Model model) {
        return "addUser";
    }


    @PostMapping("/addUser")
    public String addUser(MyUser myUser, Role role) {
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//        myUser.setRoles(roles);
        myUserService.saveMyUser(myUser,role);
        return "redirect:/allUser";
    }

    @GetMapping("/userData/{id}")
    public String userData_index(@PathVariable int id, Model model) {
        MyUser myUser = myUserService.getMyUser(id);
        model.addAttribute("myUser", myUser);
        return "userData";
    }
    @GetMapping("/user/{id}")
    public String user(@PathVariable int id, Model model) {
        MyUser myUser = myUserService.getMyUser(id);
        model.addAttribute("myUser", myUser);
        return "user";
    }

    @PostMapping("/updateUser")
    public String updateUser(MyUser myUser,Role role) {
//        Set<Role> roles = new HashSet<>();
//        System.out.println(myUser.getRoles() + "this role");
//        roles.add(role);
//        myUser.setRoles(roles);

//        myUserService.updateMyUser(myUser);
        myUserService.updateMyUser(myUser,role);
        return "redirect:/allUser";

    }

    @GetMapping("/removeUser")
    public String removeUser_index() {
        return "redirect:/allUser";
    }

    @GetMapping("/updateUser")
    public String updateUser_index() {
        return "redirect:/allUser";
    }


    @GetMapping("/removeUser/{id}")
    public String userData_index(@PathVariable int id) {
        myUserService.deleteMyUser(id);
        return "redirect:/allUser";
    }

}
