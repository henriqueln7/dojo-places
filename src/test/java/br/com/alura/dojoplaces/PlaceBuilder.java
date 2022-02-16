package br.com.alura.dojoplaces;

import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class PlaceBuilder {
    private String code = "Código";
    private String name = "Nome";
    private String district = "Distrito";
    private String city = "Cidade";

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static PlaceBuilder aPlace() {
        return new PlaceBuilder();
    }

    public PlaceBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public PlaceBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Place build() {
        Place place = new Place(this.code, this.name, this.district, this.city);
        if (this.createdAt != null) {
            ReflectionTestUtils.setField(place, "createdAt", this.createdAt);
        }
        if (this.updatedAt != null) {
            ReflectionTestUtils.setField(place, "updatedAt", this.updatedAt);
        }

        return place;
    }

}
