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

    @GetMapping("user1/{user1Id}/user2/{user2Id}")
    public ChatBoundary getChatBetweenUsers(@PathVariable("user1Id") String user1Id, @PathVariable("user2Id") String user2Id) {
        return chatService.getChatBetweenUsers(user1Id, user2Id);
    }

    @GetMapping("/all")
    public List<ChatBoundary> getAllChat(
            @RequestParam int size,
            @RequestParam int page) {
        return chatService.getAllChat(size, page);
    }

    @DeleteMapping("/delete")
    public void deleteAll() {
        chatService.deleteAll();
    }

}
