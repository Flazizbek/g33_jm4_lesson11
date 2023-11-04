package model;

import java.time.LocalDateTime;

public class Event {
private LocalDateTime startTime;
private String description;

    public Event(LocalDateTime startTime, String description) {
        this.startTime = startTime;
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
