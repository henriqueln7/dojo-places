package br.com.alura.dojoplaces;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EditPlaceValidator implements Validator {

    private final PlaceRepository placeRepository;

    public EditPlaceValidator(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return EditPlaceForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        EditPlaceForm form = (EditPlaceForm) target;

        if (placeRepository.existsByCodeAndIdNot(form.code(), form.id())) {
            errors.rejectValue("code", "place.code.already.exists", "O código do lugar já existe");
        }
    }
}
