package dev.campusfix.service;
import dev.campusfix.model.Category;
import dev.campusfix.model.Priority;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketPolicyTest {
    @Test void safetyIncidentBecomesCritical(){assertEquals(Priority.CRITICAL,TicketPolicy.priority(Category.CLASSROOM,"Exposed wire near desk"));}
    @Test void networkOutageBecomesHigh(){assertEquals(Priority.HIGH,TicketPolicy.priority(Category.NETWORK,"Wi-Fi down"));}
    @Test void cleaningIsLow(){assertEquals(Priority.LOW,TicketPolicy.priority(Category.CLEANING,"Corridor dusty"));}
    @Test void slaUsesPriority(){LocalDateTime start=LocalDateTime.of(2026,11,12,9,0);
        assertEquals(start.plusHours(4),TicketPolicy.dueAt(Priority.CRITICAL,start));
        assertEquals(start.plusHours(72),TicketPolicy.dueAt(Priority.LOW,start));}
}
