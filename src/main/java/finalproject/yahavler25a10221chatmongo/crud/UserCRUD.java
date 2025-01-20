package finalproject.yahavler25a10221chatmongo.crud;

import finalproject.yahavler25a10221chatmongo.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCRUD extends MongoRepository<UserEntity, String> {

}



