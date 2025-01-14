package finalproject.yahavler25a10221chatmongo.controllers;
import finalproject.yahavler25a10221chatmongo.boudaries.MessageBoundary;
import finalproject.yahavler25a10221chatmongo.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
            @RequestParam String conversationId,
            @RequestParam String senderId,
            @RequestParam String receiverId,
            @RequestBody String content) {
        return messageService.sendMessage(conversationId, senderId, receiverId, content);
    }

    @GetMapping("/conversation/{conversationId}")
    public List<MessageBoundary> getMessagesByConversationId(@PathVariable String conversationId) {
        return messageService.getMessagesByConversationId(conversationId);
    }

    @GetMapping("/user/{userId}")
    public List<MessageBoundary> getMessagesByUserId(@PathVariable String userId) {
        return messageService.getMessagesByUserId(userId);
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

