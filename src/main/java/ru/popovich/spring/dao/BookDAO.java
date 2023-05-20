//package ru.popovich.spring.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import ru.popovich.spring.models.Book;
//import ru.popovich.spring.models.Person;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class BookDAO {
//    JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index() {
//        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
//    }
//    public Book show(int id) {
//        return jdbcTemplate.query("SELECT * From book WHERE id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
//    }
//    public void save(Book book) {
//        jdbcTemplate.update("INSERT INTO book(name, author, publishing_year) values(?,?,?) ", book.getName(),
//                book.getAuthor(), book.getPublishingYear());
//    }
//
//    public void update(int id, Book book) {
//        jdbcTemplate.update("UPDATE book SET name=?, author=?, publishing_year=? WHERE id=?",
//                book.getName(), book.getAuthor(), book.getPublishingYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
//    }
//
//    //Join'им таблицы book и person и получаем человека, которому принадлежит книга с указанным id
//    public Optional<Person> getBookOwner(int id) {
//        return jdbcTemplate.query("SELECT person.* FROM book JOIN person ON book.person_id=person.id " +
//                "WHERE book.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }
//
//    //Освобождет книгу. Этот метод вызывается, когда человек возвращае книгу в библиотеку.
//    public void release(int id) {
//        jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE id=?", id);
//    }
//
//    //Назначет книгу человеку (этот метод вызывается, когда человек забирает книгу из библиотеки)
//    public void assign(int id, Person selectedPerson) {
//        jdbcTemplate.update("UPDATE book Set person_id=? WHERE id=?", selectedPerson.getId(), id);
//    }
//
////    public List<Book> showPersonBooks(int id) {
////        return jdbcTemplate.query("SELECT b.book_id, b.name, b.author, b.publishing_year," +
////                "p.person_id, p.full_name, p.birth_date " +
////                "FROM person p LEFT JOIN book b ON p.person_id=b.person_id WHERE p.person_id=?", new Object[]{id}, new BookRowMapper());
////    }
////
////    public List<Book> getFreePersons() {
////        return jdbcTemplate.query("SELECT b.book_id, b.name, b.author, b.publishing_year," +
////                "p.person_id, p.full_name, p.birth_date " +
////                "FROM person p LEFT JOIN book b ON p.person_id=b.person_id WHERE b.person_id is null", new Object[]{}, new BookRowMapper());
////    }
//
//    public void setPerson(int bookId, int personId) {
//        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?",
//                personId, bookId);
//    }
//
//    public void resetPerson(int bookId) {
//        jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE book_id=?", bookId);
//    }
//}
