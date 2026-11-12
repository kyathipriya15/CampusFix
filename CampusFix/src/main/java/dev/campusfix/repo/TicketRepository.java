package dev.campusfix.repo;
import dev.campusfix.model.Category;
import dev.campusfix.model.Ticket;
import dev.campusfix.model.TicketStatus;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findAllByOrderByCreatedAtDesc();
    boolean existsByBuildingIgnoreCaseAndRoomIgnoreCaseAndCategoryAndStatusNotAndCreatedAtAfter(
        String building, String room, Category category, TicketStatus status, LocalDateTime after);
}
