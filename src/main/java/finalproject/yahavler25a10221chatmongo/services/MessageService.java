package finalproject.yahavler25a10221chatmongo.services;

import finalproject.yahavler25a10221chatmongo.boudaries.MessageBoundary;

import java.util.List;

public interface MessageService {
    MessageBoundary sendMessage(MessageBoundary messageBoundary);
    List<MessageBoundary> getMessagesByUserIdToReciverId(String userId, String receiverId);
    List<MessageBoundary> getAllMessages(int size, int page);
    MessageBoundary getMessageById(String messageId);
    List<MessageBoundary> getMessagesByUserIdFromSenderId(String userId, String senderId);
    void deleteAll();
}

