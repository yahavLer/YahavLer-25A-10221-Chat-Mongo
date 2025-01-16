package finalproject.yahavler25a10221chatmongo.boudaries;

import java.time.LocalDateTime;

public class UserBoundary {
    private String id;
    private String username;
    private String phoneNumber;
    private LocalDateTime timestamp;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timeStep) {
        this.timestamp = timeStep;
    }

    @Override
    public String toString() {
        return "UserBoundary{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", timeStep=" + timestamp +
                '}';
    }
}