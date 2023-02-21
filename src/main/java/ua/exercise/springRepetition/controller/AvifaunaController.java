package ua.exercise.springRepetition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.exercise.springRepetition.DAO.BirdDAO;
import ua.exercise.springRepetition.models.Bird;

@Controller
@RequestMapping("/avifauna")
public class AvifaunaController {
    private final BirdDAO birdDAO;

    @Autowired
    public AvifaunaController(BirdDAO birdDAO) {
        this.birdDAO = birdDAO;
    }

    @GetMapping()
    String index(Model model) {
        model.addAttribute("avifauna", birdDAO.index());
        return "avifauna/index";
    }

    @GetMapping("/{id}")
    String show(Model model, @PathVariable("id") int id) {
        model.addAttribute("bird", birdDAO.show(id));
        return "avifauna/show";
    }

    @GetMapping("/new")
    String newBird(@ModelAttribute("bird") Bird bird) {
        return "avifauna/new";
    }

    @PostMapping
    String create(@ModelAttribute("bird") Bird bird) {
        birdDAO.save(bird);
        return "redirect:/avifauna";
    }

    @GetMapping("/{id}/edit")
    String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("bird", birdDAO.show(id));
        return "avifauna/edit";
    }

    @PatchMapping("/{id}")
    String update(@ModelAttribute("bird") Bird bird, @PathVariable("id") int id){
        birdDAO.update(id, bird);
        return "redirect:/avifauna";
    }

    @DeleteMapping("{id}")
    String delete(@PathVariable("id") int id){
        birdDAO.delete(id);
        return "redirect:/avifauna";
    }
}
