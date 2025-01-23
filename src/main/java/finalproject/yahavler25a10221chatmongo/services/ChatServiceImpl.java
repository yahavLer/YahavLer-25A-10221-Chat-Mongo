package finalproject.yahavler25a10221chatmongo.services;

import finalproject.yahavler25a10221chatmongo.boudaries.ChatBoundary;
import finalproject.yahavler25a10221chatmongo.boudaries.MessageBoundary;
import finalproject.yahavler25a10221chatmongo.boudaries.UserBoundary;
import finalproject.yahavler25a10221chatmongo.convertors.ChatConverter;
import finalproject.yahavler25a10221chatmongo.crud.ChatCRUD;
import finalproject.yahavler25a10221chatmongo.crud.MessageCRUD;
import finalproject.yahavler25a10221chatmongo.entities.ChatEntity;
import finalproject.yahavler25a10221chatmongo.entities.UserEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
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
        ChatBoundary existingChat = findExistingChat(user1Id, user2Id);
        if (existingChat != null) {
            throw new RuntimeException("Chat already exists between users " + user1Id + " and " + user2Id);
        }
        return createNewChat(user1Id, user2Id);
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
    public ChatBoundary addMessageToChat(String user1Id, String user2Id, MessageBoundary messageBoundary) {
        ChatBoundary chatBoundary = findExistingChat(user1Id, user2Id);
        if (chatBoundary == null) {
            System.out.println("Chat is not exists");
            chatBoundary = createNewChat(user1Id, user2Id);
        }

        messageBoundary.setId(UUID.randomUUID().toString());
        messageBoundary.setTimestamp(LocalDateTime.now());
        chatBoundary.getMessages().add(messageBoundary);

        ChatEntity updatedChatEntity = this.chatConverter.convertChatBoundaryToEntity(chatBoundary);
        this.mongoTemplate.save(updatedChatEntity);

        return this.chatConverter.convertChatEntityToBoundary(updatedChatEntity);
    }


    @Override
    public ChatBoundary getChatByUser1IdAndUser2Id(String user1Id, String user2Id) {
        ChatBoundary chatBoundary = this.mongoTemplate
                .query(ChatEntity.class)
                .inCollection("chats")
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
                .inCollection("chats")
                .as(ChatEntity.class)
                .matching(query(where("user1Id").is(userId).orOperator(where("user2Id").is(userId))))
                .all()
                .stream()
                .map(this.chatConverter::convertChatEntityToBoundary)
                .collect(Collectors.toList());
        return chatBoundaryList;
    }



    public ChatBoundary findExistingChat(String user1Id, String user2Id) {
        return this.mongoTemplate
                .query(ChatEntity.class)
                .inCollection("chats")
                .matching(query(
                        where("user1Id").is(user1Id).andOperator(where("user2Id").is(user2Id))
                                .orOperator(
                                        where("user1Id").is(user2Id).andOperator(where("user2Id").is(user1Id))
                                )
                ))
                .first()
                .map(this.chatConverter::convertChatEntityToBoundary)
                .orElse(null);
    }

    public ChatBoundary createNewChat(String user1Id, String user2Id) {
        ChatEntity newChat = new ChatEntity();
        newChat.setId(UUID.randomUUID().toString());
        newChat.setUser1Id(user1Id);
        newChat.setUser2Id(user2Id);
        newChat.setCreatedAt(LocalDateTime.now());
        newChat.setMessages(List.of());
        newChat =mongoTemplate.insert(ChatEntity.class)
                .inCollection("chats")
                .one(newChat);
        System.out.print("Chat created");
        return this.chatConverter.convertChatEntityToBoundary(newChat);
    }

    @Override
    public List<ChatBoundary> getAllChat(int size, int page) {
        Query query = new Query();
        query.limit(size);
        query.skip(size * page);
        List<ChatBoundary> chatBoundaryList = this.mongoTemplate
                .find(query, ChatBoundary.class)
                .stream()
                .toList();
        return chatBoundaryList;
    }


}
