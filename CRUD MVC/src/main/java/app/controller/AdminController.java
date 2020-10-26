package app.controller;

import app.model.User;
import app.model.Role;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/table")
    public String getTable(Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("users", userService.getMyUserList());
        return "table";
    }

    @PostMapping(value = "/table")
    public String addUser(User user, Role role) {
        System.out.println(user);
        System.out.println(role);
        userService.saveMyUser(user, role);
        return "redirect:/admin/table";
    }

    @PostMapping(value = "/remove")
    public String removeUser(@RequestParam int id) {
        userService.deleteMyUser(id);
        return "redirect:/admin/table";
    }

    @PostMapping(value = "/update")
    public String updateUser(User user, Role role) {
        user.setPassword(userService.ifPasswordNull(user.getId(), user.getPassword()));
        userService.updateMyUser(user, role);
        return "redirect:/admin/table";
    }

    @GetMapping(value = "/newUser")
    public String newUser(Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getPrincipal());
        return "/addUser";
    }
}
