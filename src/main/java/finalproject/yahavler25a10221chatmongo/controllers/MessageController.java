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
            @RequestBody  MessageBoundary messageBoundary) {
        return messageService.sendMessage(messageBoundary);
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

    @GetMapping("/all")
    public List<MessageBoundary> getAllMessages(
            @RequestParam int size,
            @RequestParam int page) {
        return messageService.getAllMessages(size, page);
    }

    @DeleteMapping("/delete")
    public void deleteAll() {
        messageService.deleteAll();
    }

}

