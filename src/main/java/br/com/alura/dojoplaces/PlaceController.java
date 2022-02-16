package br.com.alura.dojoplaces;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PlaceController {

    private final PlaceRepository placeRepository;
    private final EditPlaceValidator editPlaceValidator;
    private final CreatePlaceValidator createPlaceValidator;

    public PlaceController(PlaceRepository placeRepository,
                           EditPlaceValidator editPlaceValidator,
                           CreatePlaceValidator createPlaceValidator) {
        this.placeRepository = placeRepository;
        this.editPlaceValidator = editPlaceValidator;
        this.createPlaceValidator = createPlaceValidator;
    }


    @InitBinder("createPlaceForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(createPlaceValidator);
    }

    @InitBinder("editPlaceForm")
    public void init(WebDataBinder binder) {
        binder.addValidators(editPlaceValidator);
    }

    @GetMapping("/places/new")
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

    @GetMapping("/places/{id}/edit")
    public String editPlaceForm(@PathVariable Long id, Model model, EditPlaceForm editPlaceForm) {
        Place place = placeRepository.findById(id)
                                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        model.addAttribute("placeEditView", new PlaceEditView(place));
        return "places/editForm";
    }

    @PostMapping("/places/{id}/edit")
    public String editPlace(@PathVariable Long id, Model model, @Valid EditPlaceForm editPlaceForm, BindingResult result) {
        if (result.hasErrors()) {
            return editPlaceForm(id, model, editPlaceForm);
        }

        Place place = placeRepository.findById(id)
                                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        place.updateFrom(editPlaceForm);
        placeRepository.save(place);

        return "redirect:/places/" + id + "/edit";
    }

    @GetMapping("/places")
    public String listPlaces(Model model) {
        List<Place> places = placeRepository.findAll();
        model.addAttribute("places", places.stream().map(PlaceView::new).toList());
        return "places/list";
    }

    @DeleteMapping("/places/{id}")
    @ResponseBody
    public void deletePlace(@PathVariable Long id) {
        placeRepository.deleteById(id);
    }
}
