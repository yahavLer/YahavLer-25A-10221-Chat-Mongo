package finalproject.yahavler25a10221chatmongo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import finalproject.yahavler25a10221chatmongo.boudaries.MessageBoundary;
import finalproject.yahavler25a10221chatmongo.convertors.MessageConverter;
import finalproject.yahavler25a10221chatmongo.crud.MessageCRUD;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;
import org.springframework.transaction.annotation.Transactional;


@Service

public class MessageServiceImpl implements MessageService {
    private MessageCRUD messageCRUD;
    private MessageConverter messageConverter;
    private MongoTemplate mongoTemplate;


    public MessageServiceImpl(MessageCRUD messageCRUD, MessageConverter messageConverter, MongoTemplate mongoTemplate) {
        this.messageCRUD = messageCRUD;
        this.mongoTemplate =mongoTemplate;
        this.messageConverter = messageConverter;
    }

    @Override
    public MessageBoundary sendMessage(String conversationId, String senderId, String receiverId, String content) {
        return null;
    }

    @Override
    public List<MessageBoundary> getMessagesByConversationId(String conversationId) {
        return List.of();
    }

    @Override
    public List<MessageBoundary> getMessagesByUserId(String userId) {
        return List.of();
    }
}
