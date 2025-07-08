package com.kmdev.springcourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
    private List<Book> books = new ArrayList<>();

    @Column(name = "initials")
    private String initials;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
}
