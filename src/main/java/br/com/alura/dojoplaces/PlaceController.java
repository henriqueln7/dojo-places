package br.com.alura.dojoplaces;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PlaceController {

    private final PlaceRepository placeRepository;
    private final UniqueCodeValidator uniqueCodeValidator;

    public PlaceController(PlaceRepository placeRepository, UniqueCodeValidator uniqueCodeValidator) {
        this.placeRepository = placeRepository;
        this.uniqueCodeValidator = uniqueCodeValidator;
    }

    @InitBinder({"createPlaceForm", "editPlaceForm"})
    public void init(WebDataBinder binder) {
        binder.addValidators(uniqueCodeValidator);
    }

    @GetMapping("/places")
    public String createPlaceForm(Model model, CreatePlaceForm createPlaceForm) {
        return "places/form";
    }

    @PostMapping("/places")
    public String createPlace(@Valid CreatePlaceForm createPlaceForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors().stream().map(e -> e.getDefaultMessage()).toList());
            return createPlaceForm(model, createPlaceForm);
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
            model.addAttribute("errors", result.getAllErrors().stream().map(e -> e.getDefaultMessage()).toList());
            return editPlaceForm(id, model, editPlaceForm);
        }

        Place place = placeRepository.findById(id)
                                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        place.updateFrom(editPlaceForm);
        placeRepository.save(place);

        return "redirect:/places/" + id + "/edit";
    }
}
