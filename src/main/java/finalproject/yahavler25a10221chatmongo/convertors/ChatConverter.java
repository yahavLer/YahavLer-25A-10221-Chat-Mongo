package finalproject.yahavler25a10221chatmongo.convertors;

import finalproject.yahavler25a10221chatmongo.boudaries.ChatBoundary;
import finalproject.yahavler25a10221chatmongo.entities.ChatEntity;
import org.springframework.stereotype.Component;

@Component
public class ChatConverter {

    public ChatBoundary convertChatEntityToBoundary(ChatEntity entity) {
        ChatBoundary boundary = new ChatBoundary();
        boundary.setId(entity.getId());
        boundary.setUser1Id(entity.getUser1Id());
        boundary.setUser2Id(entity.getUser2Id());
        boundary.setMessages(entity.getMessages());
        return boundary;

    }

    public ChatEntity convertChatBoundaryToEntity(ChatBoundary boundary) {
        ChatEntity entity = new ChatEntity();
        entity.setId(boundary.getId());
        entity.setUser1Id(boundary.getUser1Id());
        entity.setUser2Id(boundary.getUser2Id());
        entity.setMessages(boundary.getMessages());
        return entity;
    }
}
