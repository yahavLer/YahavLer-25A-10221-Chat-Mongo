package finalprojectmatchmaking.yahavler25a10221;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageCRUD messageCRUD;
    private MessageConverter messageConverter;


    @Autowired
    public MessageServiceImpl(MessageCRUD messageCRUD, MessageConverter messageConverter) {
        this.messageCRUD = messageCRUD;
        this.messageConverter = messageConverter;
    }
    @Override

    public MessageBoundary saveMessage(String senderId, String receiverId, String content) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(UUID.randomUUID().toString());
        messageEntity.setSenderId(senderId);
        messageEntity.setReceiverId(receiverId);
        messageEntity.setContent(content);
        messageEntity.setTimestamp(LocalDateTime.now());
        return this.messageConverter.convertEntityToBoundary(this.messageCRUD.save(messageEntity));
    }
    @Override
    public List<MessageBoundary> getMessages(String senderId, String receiverId, int page, int size) {
        return this.messageCRUD
                .findAllBySenderIdAndReceiverId(senderId, receiverId)
                .stream()
                .map(this.messageConverter::convertEntityToBoundary)
                .collect(Collectors.toList());
    }
    @Override
    public List<MessageBoundary> searchMessagesByKey(String senderId, String receiverId, String keyword) {
        return this.messageCRUD
                .findAllBySenderIdAndReceiverIdAndContentContaining(senderId, receiverId, keyword)
                .stream()
                .map(this.messageConverter::convertEntityToBoundary)
                .collect(Collectors.toList());
    }
    @Override
    public List<MessageBoundary> getMessagesByDateRange(String senderId, String receiverId, LocalDateTime startDate, LocalDateTime endDate) {
        return this.messageCRUD
                .findAllBySenderIdAndReceiverIdAndTimestampBetween(senderId, receiverId, startDate, endDate)
                .stream()
                .map(this.messageConverter::convertEntityToBoundary)
                .collect(Collectors.toList());
    }
    @Override
    public void deleteAll() {
        this.messageCRUD.deleteAll();
    }
}
