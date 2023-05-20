//package ru.popovich.spring.dao;
//
//import org.springframework.jdbc.core.RowMapper;
//import ru.popovich.spring.models.Person;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class PersonRowMapper implements RowMapper<Person> {
//    @Override
//    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
//        Person person = new Person();
//
//        person.setId(resultSet.getInt("person_id"));
//        person.setFullName(resultSet.getString("full_name"));
//        person.setBirthDate(resultSet.getInt("birth_date"));
//
//        return person;
//    }
//}
