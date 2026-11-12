package dev.campusfix.web;

import dev.campusfix.dto.TicketRequest;
import dev.campusfix.model.*;
import dev.campusfix.service.TicketService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TicketWebController {
    private final TicketService service;
    public TicketWebController(TicketService service){this.service=service;}

    @GetMapping("/")
    public String index(@RequestParam(required=false) String status,@RequestParam(required=false) String search,Model model){
        List<Ticket> all=service.all();
        List<Ticket> filtered=all.stream().filter(t->status==null || status.isBlank() || t.getStatus().name().equalsIgnoreCase(status))
            .filter(t->search==null || search.isBlank() || (t.getTitle()+" "+t.getBuilding()+" "+t.getRoom()).toLowerCase().contains(search.toLowerCase())).toList();
        model.addAttribute("tickets",filtered);
        model.addAttribute("total",all.size());
        model.addAttribute("open",all.stream().filter(t->t.getStatus()==TicketStatus.OPEN).count());
        model.addAttribute("progress",all.stream().filter(t->t.getStatus()==TicketStatus.IN_PROGRESS).count());
        model.addAttribute("overdue",all.stream().filter(Ticket::isOverdue).count());
        model.addAttribute("status",status == null ? "" : status);
        model.addAttribute("search",search == null ? "" : search);
        return "index";
    }

    @GetMapping("/tickets/new")
    public String newTicket(Model model){model.addAttribute("categories",Category.values());return "new-ticket";}

    @PostMapping("/tickets")
    public String create(@RequestParam String title,@RequestParam String description,@RequestParam Category category,
                         @RequestParam String building,@RequestParam String room,RedirectAttributes redirect){
        try {
            if(title.isBlank() || description.isBlank() || building.isBlank() || room.isBlank() || title.length()>100 || description.length()>1000 || building.length()>80 || room.length()>24)
                throw new IllegalArgumentException("Fill in all fields within the allowed lengths");
            Ticket created=service.create(new TicketRequest(title,description,category,building,room));
            redirect.addFlashAttribute("success","Ticket #"+created.getId()+" created successfully");
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            redirect.addFlashAttribute("error",e.getMessage());
            return "redirect:/tickets/new";
        }
    }
    @GetMapping("/tickets/{id}")
    public String detail(@PathVariable long id,Model model){model.addAttribute("ticket",service.get(id));return "ticket-detail";}
    @PostMapping("/tickets/{id}/status")
    public String update(@PathVariable long id,@RequestParam TicketStatus status,RedirectAttributes redirect){
        try {service.changeStatus(id,status);redirect.addFlashAttribute("success","Ticket updated to "+status);}
        catch (IllegalArgumentException e){redirect.addFlashAttribute("error",e.getMessage());}
        return "redirect:/tickets/"+id;
    }
}
