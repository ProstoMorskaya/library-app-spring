package com.kmdev.springcourse.repositories;

import com.kmdev.springcourse.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
    Optional<Book> findByName(String name);

    @Query("""
            SELECT b 
            FROM Book b
            LEFT JOIN FETCH b.person
            WHERE b.id = :id
            """)
    Optional<Book> findByIdWithPerson(@Param("id") int id);

    @Query(value = """
            SELECT b 
            FROM Book b
            LEFT JOIN FETCH b.person
            """, countQuery = "SELECT count(b) FROM Book b")
    Page<Book> findAll(Pageable pageable);

    @Query("""
            SELECT b
            FROM Book b 
            JOIN FETCH b.person 
            WHERE LOWER(b.name) 
            LIKE LOWER(CONCAT(:prefix, '%'))
            """
    )
    List<Book> findByNameStartingWithIgnoreCase(String prefix);

}
