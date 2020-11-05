package app.controller;

import app.model.Role;
import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rest")
@RestController
public class AdminRestController {


    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/add")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userService.saveMyUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        final Iterable<User> users = userService.getMyUserList();

        return users != null
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/paste")
    public ResponseEntity<User> getUser(@RequestBody int id) {
        User user = this.userService.getMyUser(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        userService.deleteMyUser(id);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    @PutMapping(value = "/edit")
    public ResponseEntity<User> editUser(@RequestBody User user) {
        user.setPassword(userService.ifPasswordNull(user.getId(), user.getPassword()));
        Role role = user.getRoles().stream().findFirst().get();
        userService.updateMyUser(user, role.getName());
        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
    }
}




