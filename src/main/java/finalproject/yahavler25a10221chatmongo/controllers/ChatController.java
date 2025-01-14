package finalproject.yahavler25a10221chatmongo.controllers;

import finalproject.yahavler25a10221chatmongo.boudaries.ChatBoundary;
import finalproject.yahavler25a10221chatmongo.services.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/create")
    public ChatBoundary createChat(@RequestParam String user1Id, @RequestParam String user2Id) {
        return chatService.createChat(user1Id, user2Id);
    }

    @GetMapping("/{chatId}")
    public ChatBoundary getChatByChatId(@PathVariable String chatId) {
        return chatService.getChatByChatId(chatId);
    }

    @GetMapping("/user/{userId}")
    public List<ChatBoundary> getChatsByUserId(@PathVariable String userId) {
        return chatService.getChatsByUserId(userId);
    }

    @PutMapping("/{chatId}/add-message")
    public ChatBoundary addMessageToChat(@PathVariable String chatId, @RequestParam String messageId) {
        return chatService.addMessageToChat(chatId, messageId);
    }
}
