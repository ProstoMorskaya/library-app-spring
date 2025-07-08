package com.kmdev.springcourse.repositories;

import com.kmdev.springcourse.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByName(String name);

    @Query("""
            SELECT b 
            FROM Book b
            LEFT JOIN FETCH b.person
            WHERE b.id = :id
            """)
    Optional<Book> findByIdWithPerson(@Param("id") int id);
}
