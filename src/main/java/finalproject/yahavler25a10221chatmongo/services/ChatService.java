package finalproject.yahavler25a10221chatmongo.services;

import finalproject.yahavler25a10221chatmongo.boudaries.ChatBoundary;

import java.util.List;

public interface ChatService {
    ChatBoundary createChat(ChatBoundary chatBoundary,String user1Id, String user2Id);
    ChatBoundary getChatByChatId(String chatId);
    List<ChatBoundary> getChatsByUserId(String userId);
    ChatBoundary getChatBetweenUsers(String user1Id, String user2Id);
    List<ChatBoundary> getAllChat(int size, int page);
    void deleteAll();
    }
