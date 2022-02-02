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
    @Pattern(regexp = "[a-zA-Z0-9]+")
    @Column(unique = true)
    private String code;
    @NotBlank @Size(max = 100)
    private String name;
    @NotBlank @Size(max = 100)
    private String district;
    @NotBlank @Size(max = 100)
    private String city;

    private final LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @Deprecated
    protected Place(){}

    public Place(String code, String name, String district, String city) {
        this.code = code;
        this.name = name;
        this.district = district;
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public void updateFrom(EditPlaceForm form) {
        this.code = form.code();
        this.name = form.name();
        this.district = form.district();
        this.city = form.city();
        this.updatedAt = LocalDateTime.now();
    }
}
