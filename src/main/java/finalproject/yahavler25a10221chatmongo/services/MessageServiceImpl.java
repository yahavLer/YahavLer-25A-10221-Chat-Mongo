package finalproject.yahavler25a10221chatmongo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import finalproject.yahavler25a10221chatmongo.boudaries.MessageBoundary;
import finalproject.yahavler25a10221chatmongo.convertors.MessageConverter;
import finalproject.yahavler25a10221chatmongo.crud.MessageCRUD;
import finalproject.yahavler25a10221chatmongo.entities.MessageEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import static javax.management.Query.and;
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
    public MessageBoundary sendMessage(String conversationId, MessageBoundary boundary) {
        MessageEntity entity = this.messageConverter.convertMessageBoundaryToEntity(boundary);
        entity.setConversationId(conversationId);
        entity.setId(UUID.randomUUID().toString());
        entity.setTimestamp(LocalDateTime.now());
        entity = mongoTemplate.insert(MessageEntity.class).one(entity);
        return this.messageConverter.convertMessageEntityToBoundary(entity);
    }

    @Override
    public List<MessageBoundary> getMessagesByConversationId(String conversationId) {
        LocalDateTime now = LocalDateTime.now();
        List<MessageBoundary> messageBoundaryList=this.mongoTemplate
                .query(MessageEntity.class)
                .inCollection(conversationId)
                .as(MessageEntity.class)
                .matching(query(where("timestamp").lt(now)).addCriteria(where("conversationId").is(conversationId)))
                .all()
                .stream()
                .map(this.messageConverter::convertMessageEntityToBoundary)
                .toList();
        return messageBoundaryList;
    }

    @Override
    public List<MessageBoundary> getMessagesByUserId(String userId) {
        LocalDateTime now = LocalDateTime.now();
        List<MessageBoundary> messageBoundaryList=this.mongoTemplate
                .query(MessageEntity.class)
                .inCollection(userId)
                .as(MessageEntity.class)
                .matching(query(where("timestamp").lt(now).andOperator(where("senderId").is(userId),where("receiverId").is(userId))))
                .all()
                .stream()
                .map(this.messageConverter::convertMessageEntityToBoundary)
                .toList();
        return messageBoundaryList;
    }

    @Override
    public List<MessageBoundary> getAllMessages(String userId, int size, int page) {
        Query query = new Query()
                .addCriteria(
                        new Criteria().orOperator(
                                Criteria.where("senderId").is(userId),
                                Criteria.where("receiverId").is(userId)
                        )
                )
                .with(Sort.by(Sort.Order.desc("timestamp"))) // Sort by timestamp in descending order
                .skip((long) page * size) // Skip records for pagination
                .limit(size); // Limit the number of records per page
        List<MessageBoundary> messageBoundaryList = this.mongoTemplate.find(query, MessageBoundary.class);
        return messageBoundaryList;
    }
}
