package br.com.alura.dojoplaces;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PlaceController {
    @GetMapping("/places")
    public String createPlaceForm() {
        return "places/form";
    }

    @PostMapping("/places")
    public String createPlace(@Valid CreatePlaceForm createPlaceForm, BindingResult errors) {
        if (errors.hasErrors()) {
            return createPlaceForm();
        }
        Place place = createPlaceForm.toModel();
        return "redirect:/places/form";
    }
}
