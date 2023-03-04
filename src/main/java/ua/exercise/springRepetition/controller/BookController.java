package ua.exercise.springRepetition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.exercise.springRepetition.DAO.BookDAO;
import ua.exercise.springRepetition.DAO.PersonDAO;
import ua.exercise.springRepetition.models.Book;
import ua.exercise.springRepetition.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/allBooks";
    }


    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("personWhoTook", bookDAO.showWhoTake(id));
        model.addAttribute("people", personDAO.index());
        return "books/showBook";
    }

    @GetMapping("/newBook")
    public String create(@ModelAttribute("book") Book book) {
        return "books/newBook";
    }

    @PostMapping
    public String save(@ModelAttribute("book") @Valid Book book,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/newBook";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/editBook")
    public String howToEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("book") @Valid Book book,
                       BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/editBook";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id){
        bookDAO.releaseBook(id);
        return "redirect:/books/"+id;
    }
    @PatchMapping("/{id}/addHost")
    public String addHost(@ModelAttribute("person")Person person, @PathVariable("id") int id){
        bookDAO.addHost(id, person.getPerson_id());
        return "redirect:/books/"+id;
    }
}
