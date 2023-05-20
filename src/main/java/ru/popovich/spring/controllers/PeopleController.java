package ru.popovich.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import ru.popovich.spring.dao.PersonDAO;
import ru.popovich.spring.models.Book;
import ru.popovich.spring.models.Person;
import ru.popovich.spring.services.BookService;
import ru.popovich.spring.services.PeopleService;
//import ru.popovich.spring.utils.PersonValidator;

import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

//    private PersonDAO personDAO;
//    private PersonValidator personValidator;
    private final PeopleService peopleService;
    private final BookService bookService;

    @Autowired
    public PeopleController(PeopleService peopleService, BookService bookService) {
//        this.personDAO = personDAO;
//        this.personValidator = personValidator;
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        System.out.println("Service!!!");

        return "/people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "/people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
//        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "/people/new";

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
//        model.addAttribute("books", personDAO.getBooksByPersonId(id));
        List<Book> personBooks = bookService.findBooksByPersonId(id);

        model.addAttribute("books", peopleService.isExpired(personBooks));
        System.out.println(personBooks.get(0).isExpired());
        return "/people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("person", peopleService.findOne(id));
        return "/people/edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "/people/edit";

//        personDAO.update(id, person);
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
//        personDAO.delete(id);
        peopleService.delete(id);
        return "redirect:/people";
    }
}
