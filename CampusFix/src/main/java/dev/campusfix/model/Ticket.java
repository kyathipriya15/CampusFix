package dev.campusfix.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="tickets")
public class Ticket {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false,length=100) private String title;
    @Column(nullable=false,length=1000) private String description;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=24) private Category category;
    @Column(nullable=false,length=80) private String building;
    @Column(nullable=false,length=24) private String room;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Priority priority;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private TicketStatus status = TicketStatus.OPEN;
    @Column(nullable=false) private LocalDateTime createdAt;
    @Column(nullable=false) private LocalDateTime dueAt;
    private LocalDateTime resolvedAt;

    protected Ticket() { }
    public Ticket(String title,String description,Category category,String building,String room,Priority priority,LocalDateTime createdAt,LocalDateTime dueAt) {
        this.title=title; this.description=description; this.category=category; this.building=building;
        this.room=room; this.priority=priority; this.createdAt=createdAt; this.dueAt=dueAt;
    }
    public Long getId(){return id;}
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public Category getCategory(){return category;}
    public String getBuilding(){return building;}
    public String getRoom(){return room;}
    public Priority getPriority(){return priority;}
    public TicketStatus getStatus(){return status;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public LocalDateTime getDueAt(){return dueAt;}
    public LocalDateTime getResolvedAt(){return resolvedAt;}
    public boolean isOverdue(){return status != TicketStatus.RESOLVED && dueAt.isBefore(LocalDateTime.now());}
    public void changeStatus(TicketStatus next, LocalDateTime now){
        if(status==TicketStatus.RESOLVED && next != TicketStatus.RESOLVED) throw new IllegalArgumentException("Resolved tickets cannot be reopened in this demo");
        if(status==TicketStatus.OPEN && next==TicketStatus.RESOLVED || status==TicketStatus.IN_PROGRESS && next==TicketStatus.RESOLVED) resolvedAt=now;
        if(next==TicketStatus.OPEN && status!=TicketStatus.OPEN) throw new IllegalArgumentException("A ticket cannot return to OPEN");
        status=next;
    }
}
