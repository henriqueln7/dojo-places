package br.com.alura.dojoplaces;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CreatePlaceValidator implements Validator {

    private final PlaceRepository placeRepository;

    public CreatePlaceValidator(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CreatePlaceForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        CreatePlaceForm form = (CreatePlaceForm) target;

        if (placeRepository.existsByCode(form.code())) {
            errors.rejectValue("code", "place.code.already.exists", "O código do lugar já existe");
        }
    }
}
