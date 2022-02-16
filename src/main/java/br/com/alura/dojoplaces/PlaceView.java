package br.com.alura.dojoplaces;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlaceView {

    private Long id;
    private String name;
    private String code;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PlaceView(Place place) {
        id = place.getId();
        name = place.getName();
        code = place.getCode();
        createdAt = place.getCreatedAt();
        updatedAt = place.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getFormattedCreatedAt() {
        return this.createdAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
