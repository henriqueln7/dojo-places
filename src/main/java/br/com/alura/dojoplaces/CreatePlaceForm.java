package br.com.alura.dojoplaces;

import javax.validation.constraints.*;

public record CreatePlaceForm(@NotBlank @Size(max = 100) String name,
                              @NotBlank @Pattern(regexp = "[a-zA-Z0-9]+") String code,
                              @NotBlank @Size(max = 100) String district,
                              @NotBlank @Size(max = 100) String city) {
    public Place toModel() {
        return new Place(this.code, this.name, this.district, this.city);
    }
}
