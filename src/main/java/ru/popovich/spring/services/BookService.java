package ru.popovich.spring.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.popovich.spring.models.Book;
import ru.popovich.spring.models.Person;
import ru.popovich.spring.repositories.BookRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findOne(int id) {
        Optional<Book> foundPerson = bookRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Book person) {
        bookRepository.save(person);
    }

    @Transactional
    public void update(int id, Book updatedPerson) {
        updatedPerson.setId(id);
        bookRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findBooksByPersonId(int id) {
        return bookRepository.findBooksByPersonId(id);
    }
    @Transactional
    public void releaseBook(int bookId) {
        bookRepository.releaseBook(bookId);
    }

    @Transactional
    public void assignBook(Person updatedPerson, int id) {
        Date date = new Date();
        bookRepository.assignBook(updatedPerson, date, id);
    }

    public List<Book> pagination(int page, int booksPerPage, String sortByYear) {
        if(sortByYear != null && sortByYear.equals("true"))
            return bookRepository.findAll(PageRequest.
                    of(page, booksPerPage, Sort.by("publishingYear"))).getContent();

        return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> sorting() {
        return bookRepository.findAll(Sort.by("publishingYear"));
    }

    public List<Book> findByName(String phrase) {
        return bookRepository.findByNameIgnoreCaseContaining(phrase);
    }
}
