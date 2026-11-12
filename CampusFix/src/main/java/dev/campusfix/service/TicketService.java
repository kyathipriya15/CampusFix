package dev.campusfix.service;

import dev.campusfix.dto.TicketRequest;
import dev.campusfix.model.*;
import dev.campusfix.repo.TicketRepository;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {
    private final TicketRepository repo;
    private final Clock clock = Clock.systemDefaultZone();
    public TicketService(TicketRepository repo){this.repo=repo;}

    @Transactional
    public Ticket create(TicketRequest request){
        LocalDateTime now=LocalDateTime.now(clock);
        String building=request.building().trim(); String room=request.room().trim();
        if (repo.existsByBuildingIgnoreCaseAndRoomIgnoreCaseAndCategoryAndStatusNotAndCreatedAtAfter(
                building,room,request.category(),TicketStatus.RESOLVED,now.minusHours(48))) {
            throw new IllegalArgumentException("A similar active issue has already been reported in this location within 48 hours");
        }
        Priority priority=TicketPolicy.priority(request.category(),request.description());
        return repo.save(new Ticket(request.title().trim(),request.description().trim(),request.category(),
            building,room,priority,now,TicketPolicy.dueAt(priority,now)));
    }
    @Transactional(readOnly=true)
    public List<Ticket> all(){return repo.findAllByOrderByCreatedAtDesc();}
    @Transactional(readOnly=true)
    public Ticket get(long id){return repo.findById(id).orElseThrow(()->new IllegalArgumentException("Ticket not found: " + id));}
    @Transactional
    public Ticket changeStatus(long id, TicketStatus status){
        Ticket ticket=get(id);
        ticket.changeStatus(status,LocalDateTime.now(clock));
        return ticket;
    }
}
