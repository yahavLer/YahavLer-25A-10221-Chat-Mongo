package finalproject.yahavler25a10221chatmongo.crud;

import finalproject.yahavler25a10221chatmongo.entities.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageCRUD extends MongoRepository<MessageEntity, String> {

}



