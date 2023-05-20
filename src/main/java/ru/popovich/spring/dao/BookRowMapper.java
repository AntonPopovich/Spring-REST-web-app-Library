//package ru.popovich.spring.dao;
//
//import org.springframework.jdbc.core.RowCallbackHandler;
//import org.springframework.jdbc.core.RowMapper;
//import ru.popovich.spring.models.Book;
//import ru.popovich.spring.models.Person;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class BookRowMapper implements RowMapper<Book> {
//    @Override
//    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
//        Book book = new Book();
//        Person person = new Person();
//
//        person.setId(resultSet.getInt("person_id"));
//        person.setFullName(resultSet.getString("full_name"));
//        person.setBirthDate(resultSet.getInt("birth_date"));
//
//        book.setId(resultSet.getInt("book_id"));
//        book.setPerson(person);
//        book.setName(resultSet.getString("name"));
//        book.setAuthor(resultSet.getString("author"));
//        book.setPublishingYear(resultSet.getInt("publishing_year"));
//
//        return book;
//    }
//}
