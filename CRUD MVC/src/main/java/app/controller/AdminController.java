package app.controller;

import app.model.MyUser;
import app.model.Role;
import app.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private MyUserService myUserService;

    @Autowired
    public AdminController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping("/index")
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
        myUserService.saveMyUser(myUser, role);
        return "redirect:/admin/allUser";
    }

    @GetMapping("/userData/{id}")
    public String userData_index(@PathVariable int id, Model model) {
        MyUser myUser = myUserService.getMyUser(id);
        model.addAttribute("myUser", myUser);
        return "userData";
    }

    @PostMapping("/updateUser")
    public String updateUser(MyUser myUser, Role role) {
        myUser.setPassword(myUserService.ifPasswordNull(myUser.getId(), myUser.getPassword()));
        myUserService.updateMyUser(myUser, role);
        return "redirect:/admin/allUser";
    }

    @GetMapping("/removeUser")
    public String removeUser_index() {
        return "redirect:/admin/allUser";
    }

    @GetMapping("/updateUser")
    public String updateUser_index() {
        return "redirect:/admin/allUser";
    }

    @GetMapping("/removeUser/{id}")
    public String userData_index(@PathVariable int id) {
        myUserService.deleteMyUser(id);
        return "redirect:/admin/allUser";
    }

}
