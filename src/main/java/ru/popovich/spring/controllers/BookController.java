package ru.popovich.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import ru.popovich.spring.dao.BookDAO;
//import ru.popovich.spring.dao.PersonDAO;
import ru.popovich.spring.models.Book;
import ru.popovich.spring.models.Person;
import ru.popovich.spring.services.BookService;

import jakarta.validation.Valid;
import ru.popovich.spring.services.PeopleService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
//        this.bookDAO = bookDAO;
//        this.personDAO = personDAO;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) String page,
                        @RequestParam(value = "books_per_page", required = false) String booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) String sortByYear) {
        List<Book> books = bookService.findAll();

        if (page != null && booksPerPage != null) {
            books = bookService.pagination(Integer.parseInt(page), Integer.parseInt(booksPerPage), sortByYear);
        } else if (sortByYear != null && sortByYear.equals("true")) {
            books = bookService.sorting();
        }

        model.addAttribute("books", books);

        return "/books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findOne(id));

        Optional<Person> bookOwner = peopleService.findByBookListId(id);
        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", peopleService.findAll());
        return "/books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "/books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/books/new";

//        bookDAO.save(book);
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("book", bookService.findOne(id));
        return "/books/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "/books/edit";

//        bookDAO.update(id, book);
        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
//        bookDAO.delete(id);
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
//        bookDAO.release(id);
        bookService.releaseBook(id);
        // сбросить дату??
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        //У selectedPerson назначено только поле id, остальные поля null
        System.out.println("do assign");
//        bookDAO.assign(id, selectedPerson);
        bookService.assignBook(selectedPerson, id);
        System.out.println("gjckt assin");
        return "redirect:/books/" + id;
    }

//    @PatchMapping("/{id}/addToCard")
//    public String addToCard(@ModelAttribute("person") Person person,
//                          @PathVariable("id") int id) {
//        System.out.println(person.getId());
//        bookDAO.setPerson(id, person.getId());
//
//        return "redirect:/books";
//    }
//
//    @PatchMapping("/{id}/resetPerson")
//    public String resetPerson(@PathVariable("id") int id) {
//        bookDAO.resetPerson(id);
//
//        return "redirect:/books";
//    }

    @GetMapping("/search")
    public String searchPage() {
        return "books/search";
    }

    @PostMapping("/search")
    public String search(@RequestParam String phrase, Model model) {
//        List<Book> list = ;
        model.addAttribute("books", bookService.findByName(phrase));
        System.out.println(phrase);
        return "/books/search";
    }
}
