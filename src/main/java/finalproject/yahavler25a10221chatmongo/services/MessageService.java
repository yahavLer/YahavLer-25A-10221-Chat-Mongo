package finalproject.yahavler25a10221chatmongo.services;

import finalproject.yahavler25a10221chatmongo.boudaries.MessageBoundary;

import java.util.List;

public interface MessageService {
    MessageBoundary sendMessage(String conversationId, MessageBoundary messageBoundary);
    List<MessageBoundary> getMessagesByConversationId(String conversationId);
    List<MessageBoundary> getMessagesByUserIdToReciverId(String userId, String receiverId);
    List<MessageBoundary> getAllMessages(String userId, int size, int page);
    MessageBoundary getMessageById(String messageId);

    List<MessageBoundary> getMessagesByUserIdFromSenderId(String userId, String senderId);
    // List<MessageBoundary> searchMessagesByKey(String senderId, String receiverId, String keyword);
   // List<MessageBoundary> getMessagesByDateRange(String senderId, String receiverId, LocalDateTime startDate, LocalDateTime endDate);
   // void deleteAll();
}

