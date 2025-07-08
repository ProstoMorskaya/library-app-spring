package com.kmdev.springcourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @ToString.Exclude
    private Person person;

    @Column(name = "name")
    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 1, max = 50, message = "Название должно содержать от 1 до 50 символов")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "Название автора не должно быть пустым")
    @Size(min = 3, max = 40, message = "Название автора должно содержать от 3 до 40 символов")
    private String author;


    @Column(name = "year_of_publication")
    @NotNull(message = "Дата публикации не может быть null")
    @Max(value = 2025, message = "Можно добавить книгу до 2025 года выпуска")
    private Integer yearOfPublication;
}
