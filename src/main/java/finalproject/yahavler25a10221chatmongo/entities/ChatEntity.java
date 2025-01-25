package finalproject.yahavler25a10221chatmongo.entities;

import finalproject.yahavler25a10221chatmongo.boudaries.MessageBoundary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "chats")
public class ChatEntity {
    @Id
    private String id;
    private String user1Id;
    private String user2Id;
    private LocalDateTime createdAt;
    private List<MessageBoundary> messages;

    // Constructors
    public ChatEntity() {
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(String user1Id) {
        this.user1Id = user1Id;
    }

    public String getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(String user2Id) {
        this.user2Id = user2Id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<MessageBoundary> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageBoundary> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ChatEntity{" +
                "id='" + id + '\'' +
                ", user1Id='" + user1Id + '\'' +
                ", user2Id='" + user2Id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", messages=" + messages +
                '}';
    }
}
