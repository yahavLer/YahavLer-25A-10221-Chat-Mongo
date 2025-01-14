package finalproject.yahavler25a10221chatmongo.services;

import finalproject.yahavler25a10221chatmongo.boudaries.UserBoundary;

import java.util.List;

public interface UserService {
    UserBoundary createUser(String username, String phoneNumber);
    UserBoundary getUserById(String userId);
    List<UserBoundary> getAllUsers();
    UserBoundary updateUser(String userId, UserBoundary userBoundary);
    void deleteUser(String userId);
    void deleteAllUsers();
}
