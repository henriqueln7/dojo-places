package br.com.alura.dojoplaces;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record CreatePlaceForm(@NotBlank @Size(max = 100) String name,
                              @NotBlank
                              @NotBlank @Size(max = 100) String district,
                              @NotBlank @Size(max = 100) String city) {
    public Place toModel() {
        return new Place(this.name, this.district, this.city);
    }
}
