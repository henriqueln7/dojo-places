package br.com.alura.dojoplaces;

import org.junit.jupiter.api.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreatePlaceValidatorTest {

    private PlaceRepository placeRepository;
    private CreatePlaceValidator createPlaceValidator;

    @BeforeEach
    void setUp() {
        placeRepository = mock(PlaceRepository.class);
        createPlaceValidator = new CreatePlaceValidator(placeRepository);
    }

    @Test
    @DisplayName("should reject if a place with code already exists")
    void should_reject_if_a_place_with_code_already_exists() {
        when(placeRepository.existsByCode("codigo-existente")).thenReturn(true);

        CreatePlaceForm form = new CreatePlaceForm("Nome", "codigo-existente", "Bairro 1", "Cidade 2");
        Errors errors = new BeanPropertyBindingResult(form, "form");

        createPlaceValidator.validate(form, errors);

        assertThat(errors.getFieldError("code")).extracting(DefaultMessageSourceResolvable::getCode)
                                                .isEqualTo("place.code.already.exists");
    }

    @Test
    @DisplayName("should not reject if a place with code does not exist")
    void should_not_reject_if_a_place_with_code_does_not_exist() {
        when(placeRepository.existsByCode("codigo-nao-existente")).thenReturn(false);

        CreatePlaceForm form = new CreatePlaceForm("Nome", "codigo-nao-existente", "Bairro 1", "Cidade 2");
        Errors errors = new BeanPropertyBindingResult(form, "form");

        createPlaceValidator.validate(form, errors);

        assertThat(errors.getFieldError("code")).isNull();
    }

}