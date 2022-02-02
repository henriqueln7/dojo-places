package br.com.alura.dojoplaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlaceController {
    @GetMapping("/places")
    public String createPlaceForm() {
        return "places/form";
    }

    @PostMapping("/places")
    public void createPlace(CreatePlaceForm createPlaceForm) {
        Place place = createPlaceForm.toModel();
    }
}
