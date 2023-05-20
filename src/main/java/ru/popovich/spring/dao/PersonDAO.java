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
//public class PersonDAO {
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO person(full_name, birth_date) values(?, ?)", person.getFullName(),
//                person.getBirthDate());
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM person", new Object[]{}, new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id},
//                        new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
//    }
//
//    public void update(int id, Person person) {
//        jdbcTemplate.update("UPDATE person SET full_name=?, birth_date=? WHERE id=?",
//                person.getFullName(), person.getBirthDate(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
//    }
//
//    //Для валидации уникальности ФИО
//    public Optional<Person> getPersonByFullName(String fullName) {
//        return jdbcTemplate.query("SELECT * FROM person WHERE full_name=?", new Object[]{fullName},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }
//    //Здесь JOIN не нужен. И так уже получили человека с помощью отдельного метода
//    public List<Book> getBooksByPersonId(int id) {
//        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class));
//    }
//}
