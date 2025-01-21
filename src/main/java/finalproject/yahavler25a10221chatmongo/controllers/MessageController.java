package finalproject.yahavler25a10221chatmongo.controllers;
import finalproject.yahavler25a10221chatmongo.boudaries.MessageBoundary;
import finalproject.yahavler25a10221chatmongo.services.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public MessageBoundary sendMessage(
            @RequestBody  MessageBoundary messageBoundary,
            @RequestParam String conversationId) {
        return messageService.sendMessage(conversationId,messageBoundary);
    }

    @GetMapping("/conversation/{conversationId}")
    public List<MessageBoundary> getMessagesByConversationId(@PathVariable("conversationId") String conversationId) {
        return messageService.getMessagesByConversationId(conversationId);
    }

    @GetMapping("/user/{userId}/receiver/{receiverId}")
    public List<MessageBoundary> getMessagesByUserIdToReciverId(@PathVariable("userId") String userId, @RequestParam String receiverId) {
        return messageService.getMessagesByUserIdToReciverId(userId,receiverId);
    }
    @GetMapping("/user/{userId}/sender/{senderId}")
    public List<MessageBoundary> getMessagesByUserIdFromSenderId(@PathVariable("userId") String userId, @RequestParam String senderId) {
        return messageService.getMessagesByUserIdFromSenderId(userId, senderId);
    }

    @GetMapping("/{messageId}")
    public MessageBoundary getMessageById(@PathVariable("messageId") String messageId) {
        return messageService.getMessageById(messageId);
    }

    @PostMapping("/add")
    public MessageBoundary addMessage(
            @RequestParam String content,
            @RequestParam String senderId,
            @RequestParam String receiverId) {
        return messageService.addMessage(content, senderId, receiverId);
    }

    @GetMapping("/all")
    public List<MessageBoundary> getAllMessages(
            @RequestParam String userId,
            @RequestParam int size,
            @RequestParam int page) {
        return messageService.getAllMessages(userId,size, page);
    }
/*
    @GetMapping("/search")
    public List<MessageBoundary> searchMessagesByKey(
            @RequestParam String senderId,
            @RequestParam String receiverId,
            @RequestParam String keyword) {
        return messageService.searchMessagesByKey(senderId, receiverId, keyword);
    }

    @GetMapping("/date-range")
    public List<MessageBoundary> getMessagesByDateRange(
            @RequestParam String senderId,
            @RequestParam String receiverId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return messageService.getMessagesByDateRange(
                senderId, receiverId, LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
    }

    @DeleteMapping
    public String deleteAllMessages() {
        messageService.deleteAll();
        return "All messages have been deleted";
    }
 */
}

