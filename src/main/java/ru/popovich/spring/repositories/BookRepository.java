package ru.popovich.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.popovich.spring.models.Book;
import ru.popovich.spring.models.Person;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByPersonId(int id);

    @Modifying
    @Query("UPDATE Book set person=null where id=?1")
    int releaseBook(int id);

    @Modifying
    @Query("UPDATE Book Set person=?1, date=?2 WHERE id=?3")
    void assignBook(Person updatedPerson, Date date, int bookId);

    List<Book> findByNameIgnoreCaseContaining(String phrase);
}
