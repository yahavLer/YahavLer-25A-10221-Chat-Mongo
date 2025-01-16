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
    public ChatBoundary createChat(@RequestBody ChatBoundary chatBoundary,@RequestParam String user1Id, @RequestParam String user2Id) {
        return chatService.createChat(chatBoundary, user1Id, user2Id);
    }

    @GetMapping("/chat/{chatId}")
    public ChatBoundary getChatByChatId(@PathVariable ("chatId") String chatId) {
        return chatService.getChatByChatId(chatId);
    }

    @GetMapping("/user/{userId}")
    public List<ChatBoundary> getChatsByUserId(@PathVariable("userId") String userId) {
        return chatService.getChatsByUserId(userId);
    }

    @PutMapping("add-message/chat/{chatId}/message/{messageId}")
    public ChatBoundary addMessageToChat(@PathVariable("chatId") String chatId, @PathVariable("messageId") String messageId) {
        return chatService.addMessageToChat(chatId, messageId);
    }
}
