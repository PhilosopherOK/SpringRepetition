package ua.exercise.springRepetition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.exercise.springRepetition.DAO.BookDAO;
import ua.exercise.springRepetition.DAO.PersonDAO;
import ua.exercise.springRepetition.models.Book;
import ua.exercise.springRepetition.models.Person;
import ua.exercise.springRepetition.servises.BooksService;
import ua.exercise.springRepetition.servises.PeopleService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BookController(BooksService booksService, PeopleService peopleService, BookDAO bookDAO, PersonDAO personDAO) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index( @RequestParam (value = "page", required = false) int page,
                         @RequestParam (value = "books_per_page", required = false) int books_per_page,
                         @RequestParam (value = "sort_by_year", required = false) boolean sort_by_year,
                         Model model) {
        model.addAttribute("books", booksService.findAll(page, books_per_page, sort_by_year));
        return "books/allBooks";
    }


    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findOne(id));
        model.addAttribute("people", peopleService.findAll());
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
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/editBook")
    public String howToEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("book") @Valid Book book,
                       BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/editBook";
        }
        booksService.update(id,book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id){
        booksService.releaseBook(id);
        return "redirect:/books/"+id;
    }
    @PatchMapping("/{id}/addHost")
    public String addHost(@ModelAttribute("person")Person person, @PathVariable("id") int id){
        booksService.addHost(id, person);
        return "redirect:/books/"+id;
    }

    @GetMapping("/search")
    public String searchPage(){
        return "books/search";
    }

    @PostMapping("/search?startingWith=startingWith")
    public String search(@RequestParam(value = "startingWith", required = false) String startingWith, Model model){
        model.addAttribute("findBooks", booksService.findByNameStartingWith(startingWith));
        return "books/search";
    }
}
