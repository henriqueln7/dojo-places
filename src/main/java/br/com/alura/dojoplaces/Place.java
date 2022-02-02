package br.com.alura.dojoplaces;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Pattern(regexp = "/\\w/")
    private String code;
    @NotBlank @Size(max = 100)
    private String name;
    @NotBlank @Size(max = 100)
    private String district;
    @NotBlank @Size(max = 100)
    private String city;

    @Deprecated
    protected Place(){}

    public Place(String code, String name, String district, String city) {
        this.code = code;
        this.name = name;
        this.district = district;
        this.city = city;
    }
}
