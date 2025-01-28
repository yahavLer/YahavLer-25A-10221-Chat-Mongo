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
    public UserBoundary createUser(@RequestBody UserBoundary userBoundary) {
        return userService.createUser(userBoundary);
    }

    @GetMapping("/userId/{userId}")
    public UserBoundary getById(@RequestParam("userId") String userId) {
        return userService.getById(userId);
    }

    @GetMapping("/username/{username}")
    public UserBoundary getByUsernameAndPhone(@RequestParam("username") String username, @RequestParam("phoneNumber") String phoneNumber) {
        return userService.getByUsernameAndPhone(username, phoneNumber);
    }

    @GetMapping("/all")
    public List<UserBoundary> getAllUsers(
            @RequestParam int size,
            @RequestParam int page) {
        return userService.getAllUsers(size, page);
    }
}
