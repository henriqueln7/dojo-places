package br.com.alura.dojoplaces;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    //TODO Fazer regex aqui e no form para validar se não tem caracteres especiais ou espaço em branco
    private final String code;
    @NotBlank @Size(max = 100)
    private final String name;
    @NotBlank @Size(max = 100)
    private final String district;
    @NotBlank @Size(max = 100)
    private final String city;

    private final LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @Deprecated
    protected Place(){
        this.code = null;
        this.name = null;
        this.district = null;
        this.city = null;
    }

    public Place(String code, String name, String district, String city) {
        this.code = code;
        this.name = name;
        this.district = district;
        this.city = city;
    }
}
