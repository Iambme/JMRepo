package app.controller;

import app.model.MyUser;
import app.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {


    private MyUserService myUserService;

    @Autowired
    public void setMyUserService(MyUserService myUserService) {
        this.myUserService = myUserService;
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
    public String addUser(MyUser myUser) {
        myUserService.saveMyUser(myUser);
        return "redirect:/allUser";
    }


    @GetMapping("/userData/{id}")
    public String userData_index(@PathVariable int id, Model model) {
        MyUser myUser = myUserService.getMyUser(id);
        model.addAttribute("myUser", myUser);
        return "userData";
    }

/*
        @PostMapping("/updateUser")
    public String updateUser(MyUser myUser, Model model) {
        List<MyUser> collect = Store.list.stream().filter(user -> user.getName().equals(myUser.getName())).collect(Collectors.toList());
        if (collect.size() > 0) {
            MyUser managedUser = collect.get(0);
            managedUser.setLogin(myUser.getLogin());
            managedUser.setName(myUser.getName());
            managedUser.setPassword(myUser.getPassword());
            //save
            Store.list.remove(myUser);
            Store.list.add(managedUser);
            model.addAttribute("myUser", managedUser);
            return "redirect:/userData/" + managedUser.getName();
        }
        return "redirect:/allUser";

    }
*/
    @PostMapping("/updateUser")
    //public String updateUser(@RequestParam int id, @RequestParam String name, @RequestParam String login, @RequestParam String password) {
    public String updateUser(MyUser myUser) {
        myUserService.updateMyUser(myUser);
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
