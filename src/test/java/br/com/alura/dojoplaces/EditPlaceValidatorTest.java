package br.com.alura.dojoplaces;

import org.junit.jupiter.api.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EditPlaceValidatorTest {

    private PlaceRepository placeRepository;
    private EditPlaceValidator editPlaceValidator;

    @BeforeEach
    void setUp() {
        placeRepository = mock(PlaceRepository.class);
        editPlaceValidator = new EditPlaceValidator(placeRepository);
    }

    @Test
    @DisplayName("should reject if already exists another place with new code")
    void should_reject_if_already_exists_another_place_with_new_code() {
        when(placeRepository.existsByCodeAndIdNot("codigo-existente", 1L)).thenReturn(true);

        EditPlaceForm form = new EditPlaceForm("Nome", "codigo-existente", "Bairro 1", "Cidade 2", 1L);
        Errors errors = new BeanPropertyBindingResult(form, "form");

        editPlaceValidator.validate(form, errors);

        assertThat(errors.getFieldError("code")).extracting(DefaultMessageSourceResolvable::getCode)
                                                .isEqualTo("place.code.already.exists");
    }

    @Test
    @DisplayName("should not reject if does not exist another place with new code")
    void should_not_reject_if_does_not_exist_another_place_with_new_code() {
        when(placeRepository.existsByCodeAndIdNot("codigo-nao-existente", 1L)).thenReturn(false);

        EditPlaceForm form = new EditPlaceForm("Nome", "codigo-nao-existente", "Bairro 1", "Cidade 2", 1L);
        Errors errors = new BeanPropertyBindingResult(form, "form");

        editPlaceValidator.validate(form, errors);

        assertThat(errors.getFieldError("code")).isNull();
    }

}