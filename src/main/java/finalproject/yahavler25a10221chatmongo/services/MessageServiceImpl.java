package finalproject.yahavler25a10221chatmongo.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import finalproject.yahavler25a10221chatmongo.boudaries.ChatBoundary;
import finalproject.yahavler25a10221chatmongo.boudaries.MessageBoundary;
import finalproject.yahavler25a10221chatmongo.convertors.ChatConverter;
import finalproject.yahavler25a10221chatmongo.convertors.MessageConverter;
import finalproject.yahavler25a10221chatmongo.crud.ChatCRUD;
import finalproject.yahavler25a10221chatmongo.crud.MessageCRUD;
import finalproject.yahavler25a10221chatmongo.entities.ChatEntity;
import finalproject.yahavler25a10221chatmongo.entities.MessageEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;


@Service

public class MessageServiceImpl implements MessageService {
    private MessageCRUD messageCRUD;
    private MessageConverter messageConverter;
    private MongoTemplate mongoTemplate;
    private ChatConverter chatConverter;

    public MessageServiceImpl(ChatConverter chatConverter,MessageCRUD messageCRUD, MessageConverter messageConverter, MongoTemplate mongoTemplate) {
        this.messageCRUD = messageCRUD;
        this.mongoTemplate =mongoTemplate;
        this.messageConverter = messageConverter;
        this.chatConverter = chatConverter;
    }


    @Override
    public MessageBoundary sendMessage(MessageBoundary boundary) {
        MessageEntity entity = this.messageConverter.convertMessageBoundaryToEntity(boundary);
        entity.setId(UUID.randomUUID().toString());
        entity.setTimestamp(LocalDateTime.now());
        entity = this.mongoTemplate.insert(MessageEntity.class)
                .inCollection("messages")
                .one(entity);
        ChatBoundary chatBoundary = findExistingChat(entity.getSenderId(), entity.getReceiverId());
        if (chatBoundary == null) {
            chatBoundary= createNewChat(entity.getSenderId(), entity.getReceiverId());
        }

        List<MessageBoundary> mutableMessages = chatBoundary.getMessages();
        if (mutableMessages == null) {
            mutableMessages = new ArrayList<>();
        }

        mutableMessages.add(this.messageConverter.convertMessageEntityToBoundary(entity));
        chatBoundary.setMessages(mutableMessages);

        ChatEntity chatEntity = this.chatConverter.convertChatBoundaryToEntity(chatBoundary);
        this.mongoTemplate.save(chatEntity, "chats");

        System.out.println("Chat updated: " + chatBoundary);

        return this.messageConverter.convertMessageEntityToBoundary(entity);
    }

    private ChatBoundary findExistingChat(String senderId, String receiverId) {
        Query query = new Query();
        query.addCriteria(new Criteria().orOperator(
                Criteria.where("user1Id").is(senderId).and("user2Id").is(receiverId),
                Criteria.where("user1Id").is(receiverId).and("user2Id").is(senderId)
        ));

        ChatEntity chatEntity = this.mongoTemplate.findOne(query, ChatEntity.class, "chats");
        if (chatEntity == null) {
            return null;
        }
        return this.chatConverter.convertChatEntityToBoundary(chatEntity);
    }

    public ChatBoundary createNewChat(String user1Id, String user2Id) {
        ChatEntity newChat = new ChatEntity();
        newChat.setId(UUID.randomUUID().toString());
        newChat.setUser1Id(user1Id);
        newChat.setUser2Id(user2Id);
        newChat.setCreatedAt(LocalDateTime.now());
        newChat.setMessages(new ArrayList<>());
        newChat =mongoTemplate.insert(ChatEntity.class)
                .inCollection("chats")
                .one(newChat);
        System.out.print("Chat created");
        return this.chatConverter.convertChatEntityToBoundary(newChat);
    }

    @Override
    public List<MessageBoundary> getMessagesByUserIdToReciverId(String userId, String receiverId) {
        List<MessageBoundary> messageBoundaryList=this.mongoTemplate
                .query(MessageEntity.class)
                .inCollection("messages")
                .as(MessageEntity.class)
                .matching(
                        query(where("senderId").is(userId)
                                .andOperator(where("receiverId").is(receiverId))
                        ).with(Sort.by(Sort.Order.desc("timestamp")))
                )
                .all()
                .stream()
                .map(this.messageConverter::convertMessageEntityToBoundary)
                .toList();
        return messageBoundaryList;    }

    @Override
    public List<MessageBoundary> getMessagesByUserIdFromSenderId(String userId, String senderId) {
        List<MessageBoundary> messageBoundaryList=this.mongoTemplate
                .query(MessageEntity.class)
                .inCollection("messages")
                .as(MessageEntity.class)
                .matching(
                        query(where("senderId").is(senderId)
                                .andOperator(where("receiverId").is(userId))
                        ).with(Sort.by(Sort.Order.desc("timestamp")))
                )
                .all()
                .stream()
                .map(this.messageConverter::convertMessageEntityToBoundary)
                .toList();
        return messageBoundaryList;
    }

    @Override
    public List<MessageBoundary> getAllMessages(int size, int page) {
        Query query = new Query();
        query.limit(size);
        query.skip(size * page);
        List<MessageBoundary> messageBoundaryList = this.mongoTemplate
                .find(query, MessageEntity.class)
                .stream()
                .map(this.messageConverter::convertMessageEntityToBoundary)
                .toList();
        return messageBoundaryList;
    }

    @Override
    public MessageBoundary getMessageById(String messageId) {
        LocalDateTime now = LocalDateTime.now();
        MessageBoundary messageBoundary = this.mongoTemplate
                .query(MessageEntity.class)
                .inCollection("messages")
                .as(MessageEntity.class)
                .matching(query(where("timestamp").lt(now)).addCriteria(where("id").is(messageId)))
                .first()
                .map(this.messageConverter::convertMessageEntityToBoundary)
                .orElseThrow(() -> new RuntimeException("could not find message by id: " + messageId));
        return messageBoundary;
    }

    @Override
    public void deleteAll() {
        this.messageCRUD.deleteAll();
        this.mongoTemplate.remove(new Query(), MessageEntity.class);
    }
}



