package finalprojectmatchmaking.yahavler25a10221;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageCRUD extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findAllBySenderIdAndReceiverId(String senderId, String receiverId);

    List<MessageEntity> findAllBySenderIdAndReceiverIdAndContentContaining(String senderId, String receiverId, String keyword);

    List<MessageEntity> findAllBySenderIdAndReceiverIdAndTimestampBetween(String senderId, String receiverId, LocalDateTime startDate, LocalDateTime endDate);

    void deleteAll();
}



