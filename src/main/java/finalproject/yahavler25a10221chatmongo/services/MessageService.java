package finalprojectmatchmaking.yahavler25a10221;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageService {
    MessageBoundary saveMessage(String senderId, String receiverId, String content);
    List<MessageBoundary> getMessages(String senderId, String receiverId, int page, int size);
    List<MessageBoundary> searchMessagesByKey(String senderId, String receiverId, String keyword);
    List<MessageBoundary> getMessagesByDateRange(String senderId, String receiverId, LocalDateTime startDate, LocalDateTime endDate);
    void deleteAll();
}

