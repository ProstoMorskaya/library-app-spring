package com.kmdev.springcourse.controllers;

import com.kmdev.springcourse.models.Book;
import com.kmdev.springcourse.models.Person;
import com.kmdev.springcourse.services.BooksService;
import com.kmdev.springcourse.services.PeopleService;
import com.kmdev.springcourse.util.BookCreateValidator;
import com.kmdev.springcourse.util.BookUpdateValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BookCreateValidator bookCreateValidator;
    private final BookUpdateValidator bookUpdateValidator;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@ModelAttribute("human") Person person, @PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findByIdWithPerson(id));
        model.addAttribute("people", peopleService.findAll());
        return "books/show";
    }

    @PatchMapping("/{id}")
    public String addReader(@PathVariable("id") int id, @RequestParam("personId") int personId) {
        booksService.addReader(id, personId);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String releaseBook(@PathVariable("id") int id) {
        booksService.releaseBook(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookCreateValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookUpdateValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}
