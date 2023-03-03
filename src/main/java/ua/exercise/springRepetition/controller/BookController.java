package ua.exercise.springRepetition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.exercise.springRepetition.DAO.BookDAO;
import ua.exercise.springRepetition.models.Book;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;

    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/allBooks";
    }


    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
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

    @PostMapping("/{id}")
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
}
