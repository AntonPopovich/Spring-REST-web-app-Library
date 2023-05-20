package ru.popovich.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Строка с именем не может быть пустой")
    @Size(min = 5, max = 40, message = "Имя должно находится в пределах от 5 до 40 символов")
    @Column(name = "full_name")
    private String fullName;
    @Min(value = 1900, message = "Дата рождения должна быть больше 1900")
    @Column(name = "birth_date")
    private int birthDate;

    @OneToMany(mappedBy = "person")
    private List<Book> bookList;

    public Person(String fullName, int birthDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
