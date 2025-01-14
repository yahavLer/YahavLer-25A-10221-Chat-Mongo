package finalproject.yahavler25a10221chatmongo.controllers;

import finalproject.yahavler25a10221chatmongo.boudaries.UserBoundary;
import finalproject.yahavler25a10221chatmongo.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserBoundary createUser(@RequestParam String username, @RequestParam String phoneNumber) {
        return userService.createUser(username, phoneNumber);
    }

    @GetMapping("/{userId}")
    public UserBoundary getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping
    public List<UserBoundary> getAllUsers() {
        return userService.getAllUsers();
    }
}
