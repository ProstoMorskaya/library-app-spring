package com.kmdev.springcourse.controllers;

import com.kmdev.springcourse.models.Book;
import com.kmdev.springcourse.models.Person;
import com.kmdev.springcourse.services.BooksService;
import com.kmdev.springcourse.services.PeopleService;
import com.kmdev.springcourse.util.BookCreateValidator;
import com.kmdev.springcourse.util.BookUpdateValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BookCreateValidator bookCreateValidator;
    private final BookUpdateValidator bookUpdateValidator;

    @GetMapping()
    public String index(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                        @RequestParam(value = "books_per_page", required = false, defaultValue = "10") Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false, defaultValue = "false") boolean sortByYear,
                        Model model) {
        Sort sorting = sortByYear ? Sort.by("yearOfPublication").ascending() : Sort.unsorted();
        Pageable pageable = PageRequest.of(page, booksPerPage, sorting);
        Page<Book> pages = booksService.findAll(pageable);
        var totalCountBooks = pages.getTotalElements();
        var totalPages = pages.getTotalPages();
        var books = pages.getContent();

        model.addAttribute("books", books);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("booksPerPage", booksPerPage);
        model.addAttribute("sortByYear", sortByYear);
        model.addAttribute("totalCountBooks", totalCountBooks);
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@ModelAttribute("person") Person person, @PathVariable("id") int id, Model model) {
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

    @GetMapping("/search")
    public String search(@RequestParam(value = "prefix", required = false) String prefix, Model model) {
        List<Book> books;

        if (prefix == null || prefix.isBlank()) {
            books = List.of();
            prefix = "";
        } else {
            books = booksService.searchBooksByPrefix(prefix);
        }

        model.addAttribute("books", books);
        model.addAttribute("prefix", prefix);
        return "books/search";
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
