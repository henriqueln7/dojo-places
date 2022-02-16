package br.com.alura.dojoplaces;

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

    }

}
