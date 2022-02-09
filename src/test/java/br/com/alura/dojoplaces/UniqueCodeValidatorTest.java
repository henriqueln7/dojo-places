package br.com.alura.dojoplaces;

import org.junit.jupiter.api.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UniqueCodeValidatorTest {

    private PlaceRepository placeRepository;
    private UniqueCodeValidator uniqueCodeValidator;

    @BeforeEach
    void setUp() {
        placeRepository = mock(PlaceRepository.class);
        uniqueCodeValidator = new UniqueCodeValidator(placeRepository);
    }

    @Test
    @DisplayName("should reject if a place with same code already exists")
    void should_reject_if_a_place_with_same_code_already_exists() {
        when(placeRepository.existsByCode("codigo-existente")).thenReturn(true);

        CreatePlaceForm form = new CreatePlaceForm("Nome", "codigo-existente", "Bairro 1", "Cidade 2");
        Errors errors = new BeanPropertyBindingResult(form, "form");

        uniqueCodeValidator.validate(form, errors);

        assertThat(errors.getAllErrors()).extracting(DefaultMessageSourceResolvable::getCode)
                                         .containsExactly("place.code.already.exists");
    }

    @Test
    @DisplayName("should not reject if a place with same code does not exist")
    void should_not_reject_if_a_place_with_same_code_does_not_exist() {
        when(placeRepository.existsByCode("codigo-nao-existente")).thenReturn(false);

        CreatePlaceForm form = new CreatePlaceForm("Nome", "codigo-nao-existente", "Bairro 1", "Cidade 2");
        Errors errors = new BeanPropertyBindingResult(form, "form");

        uniqueCodeValidator.validate(form, errors);

        assertThat(errors.getAllErrors()).isEmpty();
    }

}