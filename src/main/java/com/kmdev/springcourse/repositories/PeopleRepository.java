package com.kmdev.springcourse.repositories;

import com.kmdev.springcourse.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByInitials(String initials);

    @Query("""
            SELECT p 
            FROM Person p 
            LEFT JOIN FETCH p.books
            WHERE p.id = :id 
            """)
    Optional<Person> findByIdWithBooks(@Param("id") int id);
}
