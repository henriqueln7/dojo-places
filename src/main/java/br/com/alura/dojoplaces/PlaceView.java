package br.com.alura.dojoplaces;

import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class PlaceView {

    private final Long id;
    private final String name;
    private final String code;
    private final LocalDateTime createdAt;
    @Nullable private final LocalDateTime updatedAt;

    public PlaceView(Place place) {
        id = place.getId();
        name = place.getName();
        code = place.getCode();
        createdAt = place.getCreatedAt();
        updatedAt = place.getUpdatedAt().orElse(null);
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

    public String getFormattedCreatedAt() {
        return this.createdAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getDaysSinceLastUpdate() {
        if (this.updatedAt == null) {
            return "-";
        }

        long daysSinceLastUpdated = DAYS.between(this.updatedAt, LocalDateTime.now());

        if (daysSinceLastUpdated == 0) {
            return "Hoje";
        }

        if (daysSinceLastUpdated == 1) {
            return daysSinceLastUpdated  + " dia atrás";
        }

        return daysSinceLastUpdated + " dias atrás";
    }
}
