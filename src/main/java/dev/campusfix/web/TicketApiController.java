package dev.campusfix.web;

import dev.campusfix.dto.TicketRequest;
import dev.campusfix.model.Ticket;
import dev.campusfix.model.TicketStatus;
import dev.campusfix.service.TicketService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketApiController {
    private final TicketService service;
    public TicketApiController(TicketService service){this.service=service;}
    @GetMapping public List<Ticket> list(){return service.all();}
    @GetMapping("/{id}") public Ticket detail(@PathVariable long id){return service.get(id);}
    @PostMapping public ResponseEntity<Ticket> create(@Valid @RequestBody TicketRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }
    @PatchMapping("/{id}/status") public Ticket change(@PathVariable long id,@RequestBody Map<String,String> input){
        if (!input.containsKey("status")) throw new IllegalArgumentException("Provide the status field");
        return service.changeStatus(id,TicketStatus.valueOf(input.get("status").toUpperCase()));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> badRequest(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
    }
}
