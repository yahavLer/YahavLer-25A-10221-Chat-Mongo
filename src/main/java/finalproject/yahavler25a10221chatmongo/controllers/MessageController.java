package finalproject.yahavler25a10221chatmongo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public MessageBoundary sendMessage(
            @RequestParam String senderId,
            @RequestParam String receiverId,
            @RequestParam String content
    ) {
        return messageService.saveMessage(senderId, receiverId, content);
    }

    @GetMapping("/messages")
    public List<MessageBoundary> getMessages(@RequestParam(name = "senderId") String senderId,
                                             @RequestParam(name = "receiverId") String receiverId,
                                             @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                             @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return messageService.getMessages(senderId, receiverId, page, size);
    }

    @GetMapping("/messages/ByKey")
    public List<MessageBoundary> searchMessagesByKey(@RequestParam(name = "senderId") String senderId,
                                                     @RequestParam(name = "receiverId") String receiverId,
                                                     @RequestParam(name = "keyword") String keyword) {
        return messageService.searchMessagesByKey(senderId, receiverId, keyword);
    }

    @GetMapping("/messages/byDate")
    public List<MessageBoundary> getMessagesByDateRange(
            @RequestParam String senderId,
            @RequestParam String receiverId,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return messageService.getMessagesByDateRange(senderId, receiverId, start, end);
    }

    @DeleteMapping("/messages")
    public void deleteAll() {
        this.messageService.deleteAll();
    }

}
