package ua.exercise.springRepetition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.exercise.springRepetition.DAO.PersonDAO;
import ua.exercise.springRepetition.models.Person;
import ua.exercise.springRepetition.servises.PeopleService;
import ua.exercise.springRepetition.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;
    private final PersonValidator personValidator;
    private final PeopleService peopleService;

    @Autowired
    public PersonController(PersonDAO personDAO, PersonValidator personValidator, PeopleService peopleService) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/allPeople";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/showPerson";
    }

    @GetMapping("/newPerson")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/newPerson";
    }

    @PostMapping()
    public String save(@ModelAttribute("person") @Valid Person person,
                       BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/newPerson";
        }
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/editPerson")
    public String whatEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/editPerson";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("person") @Valid Person person,
                       BindingResult bindingResult,
                       @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/editPerson";
        }
        peopleService.update(id, person);
        return "redirect:/people/{id}";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
