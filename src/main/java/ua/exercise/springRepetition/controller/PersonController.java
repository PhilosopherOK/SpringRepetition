package ua.exercise.springRepetition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.exercise.springRepetition.DAO.PersonDAO;
import ua.exercise.springRepetition.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/allPeople";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", personDAO.showBusyBooks(id));
        return "people/showPerson";
    }

    @GetMapping("/newPerson")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/newPerson";
    }

    @PostMapping()
    public String save(@ModelAttribute("person") @Valid Person person,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/newPerson";
        }
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/editPerson")
    public String whatEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/editPerson";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("person") @Valid Person person,
                       BindingResult bindingResult,
                       @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "people/editPerson";
        }
        personDAO.update(id, person);
        return "redirect:/people/{id}";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
