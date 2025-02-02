package finalproject.yahavler25a10221chatmongo.services;

import finalproject.yahavler25a10221chatmongo.boudaries.UserBoundary;
import finalproject.yahavler25a10221chatmongo.convertors.UserConverter;
import finalproject.yahavler25a10221chatmongo.crud.UserCRUD;
import finalproject.yahavler25a10221chatmongo.entities.UserEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

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
    public UserBoundary createUser(UserBoundary userBoundary) {
        UserEntity userEntity = this.userConverter.convertUserBoundaryToEntity(userBoundary);
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setTimestamp(LocalDateTime.now());
        userEntity = mongoTemplate.insert(UserEntity.class)
                .inCollection("users")
                .one(userEntity);
        return this.userConverter.convertUserEntityToBoundary(userEntity);
    }

    @Override
    public UserBoundary getById(String userId) {
        UserBoundary userBoundary = this.mongoTemplate
                .query(UserEntity.class)
                .inCollection("users")
                .as(UserEntity.class)
                .matching(query(where("id").is(userId)))
                .first()
                .map(this.userConverter::convertUserEntityToBoundary)
                .orElseThrow(() -> new RuntimeException("could not find user by id: " + userId));
        return userBoundary;
    }

    @Override
    public List<UserBoundary> getAllUsers(int size, int page) {
        Query query = new Query();
        query.limit(size);
        query.skip(size * page);
        List<UserBoundary> userBoundaryList = this.mongoTemplate
                .find(query, UserEntity.class)
                .stream()
                .map(this.userConverter::convertUserEntityToBoundary)
                .toList();
        return userBoundaryList;
    }

    @Override
    public void deleteUser(String userId) {
        this.userCRUD.deleteById(userId);
        this.mongoTemplate.remove(query(where("id").is(userId)), UserEntity.class);
    }

    @Override
    public void deleteAllUsers() {
        this.userCRUD.deleteAll();
        this.mongoTemplate.remove(new Query(), UserEntity.class);
    }

    @Override
    public UserBoundary getByUsernameAndPhone(String username, String phoneNumber) {
        UserBoundary userBoundary = this.mongoTemplate
                .query(UserEntity.class)
                .as(UserEntity.class)
                .matching(
                        query(where("username").is(username)
                                .and("phoneNumber").is(phoneNumber))
                )
                .first()
                .map(this.userConverter::convertUserEntityToBoundary)
                .orElseThrow(() -> new RuntimeException("Could not find user with username: " + username + " and phone number: " + phoneNumber));
        return userBoundary;
    }

}
