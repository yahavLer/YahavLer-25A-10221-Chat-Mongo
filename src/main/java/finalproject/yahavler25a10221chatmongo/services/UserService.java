package finalproject.yahavler25a10221chatmongo.services;

import finalproject.yahavler25a10221chatmongo.boudaries.UserBoundary;

import java.util.List;

public interface UserService {
    UserBoundary createUser(UserBoundary userBoundary);
    UserBoundary getById(String userId);
    List<UserBoundary> getAllUsers(int size, int page);
    void deleteUser(String userId);
    void deleteAllUsers();
    UserBoundary getByUsername(String username);
}
