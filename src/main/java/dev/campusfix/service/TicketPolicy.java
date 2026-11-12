package dev.campusfix.service;
import dev.campusfix.model.Category;
import dev.campusfix.model.Priority;
import java.time.LocalDateTime;
import java.util.Locale;

public final class TicketPolicy {
    private TicketPolicy() { }
    public static Priority priority(Category category, String description) {
        String s = description == null ? "" : description.toLowerCase(Locale.ROOT);
        if (s.contains("sparks") || s.contains("electric shock") || s.contains("exposed wire") || s.contains("fire")) return Priority.CRITICAL;
        if (category == Category.ELECTRICAL || category == Category.NETWORK) return Priority.HIGH;
        if (category == Category.PLUMBING || category == Category.CLASSROOM) return Priority.MEDIUM;
        return Priority.LOW;
    }
    public static LocalDateTime dueAt(Priority priority,LocalDateTime createdAt) {
        return createdAt.plusHours(switch(priority){
            case CRITICAL -> 4;
            case HIGH -> 24;
            case MEDIUM -> 48;
            case LOW -> 72;
        });
    }
}
