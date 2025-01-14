package finalproject.yahavler25a10221chatmongo.convertors;

import finalproject.yahavler25a10221chatmongo.boudaries.MessageBoundary;
import finalproject.yahavler25a10221chatmongo.entities.MessageEntity;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class MessageConverter {

    public MessageEntity convertMessageBoundaryToEntity(MessageBoundary messageBoundary) {
        MessageEntity entity = new MessageEntity();
        entity.setId(messageBoundary.getId());
        entity.setSenderId(messageBoundary.getSenderId());
        entity.setReceiverId(messageBoundary.getReceiverId());
        entity.setContent(messageBoundary.getContent());
        entity.setTimestamp(messageBoundary.getTimestamp());
        return entity;
    }

    public MessageBoundary convertMessageEntityToBoundary(MessageEntity messageEntity) {
        MessageBoundary boundary = new finalproject.yahavler25a10221chatmongo.boudaries.MessageBoundary();
        boundary.setId(messageEntity.getId());
        boundary.setSenderId(messageEntity.getSenderId());
        boundary.setReceiverId(messageEntity.getReceiverId());
        boundary.setContent(messageEntity.getContent());
        boundary.setTimestamp(messageEntity.getTimestamp());
        return boundary;
    }
}
