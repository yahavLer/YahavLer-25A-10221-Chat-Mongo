package finalproject.yahavler25a10221chatmongo.services;

import finalproject.yahavler25a10221chatmongo.boudaries.ChatBoundary;
import finalproject.yahavler25a10221chatmongo.convertors.ChatConverter;
import finalproject.yahavler25a10221chatmongo.crud.ChatCRUD;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public ChatBoundary createChat(String user1Id, String user2Id) {
        return null;
    }

    @Override
    public ChatBoundary getChatByChatId(String chatId) {
        return null;
    }

    @Override
    public ChatBoundary addMessageToChat(String chatId, String messageId) {
        return null;
    }

    @Override
    public List<ChatBoundary> getChatsByUserId(String userId) {
        return null;
    }
}
