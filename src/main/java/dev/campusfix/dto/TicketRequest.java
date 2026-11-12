package dev.campusfix.dto;
import dev.campusfix.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TicketRequest(
    @NotBlank @Size(max=100) String title,
    @NotBlank @Size(max=1000) String description,
    @NotNull Category category,
    @NotBlank @Size(max=80) String building,
    @NotBlank @Size(max=24) String room
) { }
