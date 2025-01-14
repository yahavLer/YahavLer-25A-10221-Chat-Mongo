package finalproject.yahavler25a10221chatmongo.convertors;

import finalproject.yahavler25a10221chatmongo.boudaries.UserBoundary;
import finalproject.yahavler25a10221chatmongo.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component

public class UserConverter {
    public UserEntity convertUserBoundaryToEntity(UserBoundary userBoundary) {
        UserEntity entity = new UserEntity();
        entity.setId(userBoundary.getId());
        entity.setUsername(userBoundary.getUsername());
        entity.setPhoneNumber(userBoundary.getPhoneNumber());
        return entity;
    }

    public UserBoundary convertUserEntityToBoundary(UserEntity userEntity) {
        UserBoundary boundary = new UserBoundary();
        boundary.setId(userEntity.getId());
        boundary.setUsername(userEntity.getUsername());
        boundary.setPhoneNumber(userEntity.getPhoneNumber());
        return boundary;
    }


}
