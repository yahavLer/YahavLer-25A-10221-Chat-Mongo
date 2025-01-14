package finalprojectmatchmaking.yahavler25a10221;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MessageConverter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public MessageBoundary convertEntityToBoundary(MessageEntity messageEntity){
        MessageBoundary messageBoundary = new MessageBoundary();
        messageBoundary.setSenderId(messageEntity.getSenderId());
        messageBoundary.setReceiverId(messageEntity.getReceiverId());
        messageBoundary.setContent(messageEntity.getContent());
        messageBoundary.setTimestamp(messageEntity.getTimestamp());
        return messageBoundary;
    }
    public MessageEntity convertBoundaryToEntity(MessageBoundary messageBoundary){
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setSenderId(messageBoundary.getSenderId());
        messageEntity.setReceiverId(messageBoundary.getReceiverId());
        messageEntity.setContent(messageBoundary.getContent());
        messageEntity.setTimestamp(messageBoundary.getTimestamp());
        return messageEntity;
    }



}
