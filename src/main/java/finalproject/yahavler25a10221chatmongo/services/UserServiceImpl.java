package finalproject.yahavler25a10221chatmongo.services;

import finalproject.yahavler25a10221chatmongo.boudaries.UserBoundary;
import finalproject.yahavler25a10221chatmongo.convertors.ChatConverter;
import finalproject.yahavler25a10221chatmongo.convertors.UserConverter;
import finalproject.yahavler25a10221chatmongo.crud.ChatCRUD;
import finalproject.yahavler25a10221chatmongo.crud.UserCRUD;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserCRUD userCRUD;
    private MongoTemplate mongoTemplate;
    private UserConverter userConverter;

    public UserServiceImpl(UserCRUD userCRUD, MongoTemplate mongoTemplate, UserConverter userConverter) {
        this.userCRUD = userCRUD;
        this.mongoTemplate = mongoTemplate;
        this.userConverter = userConverter;
    }

    @Override
    public UserBoundary createUser(String username, String phoneNumber) {
        return null;
    }

    @Override
    public UserBoundary getUserById(String userId) {
        return null;
    }

    @Override
    public List<UserBoundary> getAllUsers() {
        return null;
    }

    @Override
    public UserBoundary updateUser(String userId, UserBoundary userBoundary) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public void deleteAllUsers() {

    }

}
