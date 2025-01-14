package finalproject.yahavler25a10221chatmongo.services;

import finalproject.yahavler25a10221chatmongo.boudaries.ChatBoundary;

import java.util.List;

public interface ChatService {
    ChatBoundary createChat(String user1Id, String user2Id);
    ChatBoundary getChatByChatId(String chatId);
    List<ChatBoundary> getChatsByUserId(String userId);
    ChatBoundary addMessageToChat(String chatId, String messageId);
    //void deleteChat(String chatId);
}
