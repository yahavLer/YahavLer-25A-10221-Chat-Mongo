package finalproject.yahavler25a10221chatmongo.services;

import finalproject.yahavler25a10221chatmongo.boudaries.ChatBoundary;
import finalproject.yahavler25a10221chatmongo.boudaries.MessageBoundary;

import java.util.List;

public interface ChatService {
    ChatBoundary createChat(ChatBoundary chatBoundary,String user1Id, String user2Id);
    ChatBoundary getChatByChatId(String chatId);
    List<ChatBoundary> getChatsByUserId(String userId);
    ChatBoundary addMessageToChat(String chatId, MessageBoundary messageBoundary);
    ChatBoundary getChatByUser1IdAndUser2Id(String user1Id, String user2Id);
    //void deleteChat(String chatId);
}
