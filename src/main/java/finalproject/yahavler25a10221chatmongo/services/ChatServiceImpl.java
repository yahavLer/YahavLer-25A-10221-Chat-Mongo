package finalproject.yahavler25a10221chatmongo.services;

import finalproject.yahavler25a10221chatmongo.boudaries.ChatBoundary;
import finalproject.yahavler25a10221chatmongo.boudaries.MessageBoundary;
import finalproject.yahavler25a10221chatmongo.convertors.ChatConverter;
import finalproject.yahavler25a10221chatmongo.crud.ChatCRUD;
import finalproject.yahavler25a10221chatmongo.crud.MessageCRUD;
import finalproject.yahavler25a10221chatmongo.entities.ChatEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class ChatServiceImpl implements ChatService{
    private ChatCRUD chatCRUD;
    private MongoTemplate mongoTemplate;
    private ChatConverter chatConverter;

    public ChatServiceImpl(ChatCRUD chatCRUD, MongoTemplate mongoTemplate, ChatConverter chatConverter) {
        this.chatCRUD = chatCRUD;
        this.mongoTemplate = mongoTemplate;
        this.chatConverter = chatConverter;
    }

    @Override
    public ChatBoundary createChat(ChatBoundary chatBoundary, String user1Id, String user2Id) {
        ChatEntity chatEntity = this.chatConverter.convertChatBoundaryToEntity(chatBoundary);
        chatEntity.setUser1Id(user1Id);
        chatEntity.setUser2Id(user2Id);
        chatEntity.setId(UUID.randomUUID().toString());
        chatEntity.setCreatedAt(LocalDateTime.now());
        chatEntity = this.mongoTemplate.insert(ChatEntity.class).one(chatEntity);
        return this.chatConverter.convertChatEntityToBoundary(chatEntity);
    }

    @Override
    public ChatBoundary getChatByChatId(String chatId) {
        LocalDateTime now = LocalDateTime.now();
        ChatBoundary chatBoundary = this.mongoTemplate
                .query(ChatEntity.class)
                .inCollection(chatId)
                .as(ChatEntity.class)
                .matching(query(where("createdAt").lt(now)).addCriteria(where("id").is(chatId)))
                .first()
                .map(this.chatConverter::convertChatEntityToBoundary)
                .orElseThrow(() -> new RuntimeException("could not find chat by id: " + chatId));
        return chatBoundary;
    }

    @Override
    public ChatBoundary addMessageToChat(String chatId, MessageBoundary messageBoundary) {
        ChatBoundary chatBoundary = this.getChatByChatId(chatId);
        chatBoundary.getMessages().add(messageBoundary);
        ChatEntity chatEntity = this.chatConverter.convertChatBoundaryToEntity(chatBoundary);
        chatEntity = this.mongoTemplate.save(chatEntity);
        return this.chatConverter.convertChatEntityToBoundary(chatEntity);
    }

    @Override
    public ChatBoundary getChatByUser1IdAndUser2Id(String user1Id, String user2Id) {
        ChatBoundary chatBoundary = this.mongoTemplate
                .query(ChatEntity.class)
                .as(ChatEntity.class)
                .matching(query(where("user1Id").is(user1Id).andOperator(where("user2Id").is(user2Id))))
                .first()
                .map(this.chatConverter::convertChatEntityToBoundary)
                .orElseThrow(() -> new RuntimeException("could not find chat by user1Id: " + user1Id + " and user2Id: " + user2Id));
        return chatBoundary;
    }

    @Override
    public List<ChatBoundary> getChatsByUserId(String userId) {
        List<ChatBoundary> chatBoundaryList = this.mongoTemplate
                .query(ChatEntity.class)
                .inCollection(userId)
                .as(ChatEntity.class)
                .matching(query(where("user1Id").is(userId).orOperator(where("user2Id").is(userId))))
                .all()
                .stream()
                .map(this.chatConverter::convertChatEntityToBoundary)
                .collect(Collectors.toList());
        return chatBoundaryList;
    }
}
