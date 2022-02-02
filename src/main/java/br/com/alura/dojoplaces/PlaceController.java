package br.com.alura.dojoplaces;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PlaceController {

    private final PlaceRepository placeRepository;

    public PlaceController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @GetMapping("/places")
    public String createPlaceForm() {
        return "places/form";
    }

    @PostMapping("/places")
    public String createPlace(@Valid CreatePlaceForm createPlaceForm) {
        Place newPlace = createPlaceForm.toModel();
        placeRepository.save(newPlace);
        return "redirect:/places/";
    }
}
