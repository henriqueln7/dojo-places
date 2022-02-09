package br.com.alura.dojoplaces;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UniqueCodeValidator implements Validator {

    private final PlaceRepository placeRepository;

    public UniqueCodeValidator(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return HasCode.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        HasCode form = (HasCode) target;

        if (placeRepository.existsByCode(form.code())) {
            errors.reject("place.code.already.exists", "O código do lugar já existe");
        }
    }
}
