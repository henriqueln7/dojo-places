package br.com.alura.dojoplaces;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Controller
public class PlaceController {

    private final PlaceRepository placeRepository;

    public PlaceController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @GetMapping("/places")
    public String createPlaceForm(CreatePlaceForm createPlaceForm) {
        return "places/form";
    }

    @PostMapping("/places")
    public String createPlace(@Valid CreatePlaceForm createPlaceForm, BindingResult result) {
        if (result.hasErrors()) {
            return createPlaceForm(createPlaceForm);
        }
        Place newPlace = createPlaceForm.toModel();
        placeRepository.save(newPlace);
        return "redirect:/places/";
    }

    @GetMapping("/places/{id}")
    public String editPlaceForm(@PathVariable Long id, Model model) {

        Place place = placeRepository.findById(id)
                                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));



        model.addAttribute("placeEditView", new PlaceEditView(place));
        return "places/editForm";
    }
}
