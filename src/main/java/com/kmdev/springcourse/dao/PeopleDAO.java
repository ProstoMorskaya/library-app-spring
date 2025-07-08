package com.kmdev.springcourse.dao;

import com.kmdev.springcourse.models.Book;
import com.kmdev.springcourse.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PeopleDAO {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public List<Person> index() {
//        return jdbcTemplate.query("""
//                SELECT id,
//                initials,
//                date_of_birth
//                FROM Person""", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id) {
//        Map<Integer, Person> personMap = new LinkedHashMap<>();
//
//        jdbcTemplate.query("""
//                SELECT p.id AS p_id,
//                p.initials,
//                p.date_of_birth,
//                p.id,
//                b.person_id,
//                b.name,
//                b.author,
//                b.year_of_publication
//                FROM Person AS p
//                LEFT JOIN Book AS b ON p.id = b.person_id
//                WHERE p.id=?""", new Object[]{id}, rs -> {
//
//            int personId = rs.getInt("p_id");
//            Person person = personMap.computeIfAbsent(personId, it -> {
//                Person person1 = new Person();
//                person1.setId(it);
//                try {
//                    person1.setInitials(rs.getString("initials"));
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//                try {
//                    person1.setDateOfBirth(rs.getDate("date_of_birth"));
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//                return person1;
//            });
//
//            int bookId = rs.getInt("id");
//            if (bookId != 0) {
//                Book book = new Book();
//                book.setId(bookId);
//                book.setPerson(person);
//                book.setName(rs.getString("name"));
//                book.setAuthor(rs.getString("author"));
//                book.setYearOfPublication(rs.getInt("year_of_publication"));
//                person.getBooks().add(book);
//            }
//        });
//
//        return personMap.values()
//                .stream()
//                .findAny()
//                .orElse(null);
//    }
//
//    public Optional<Person> show(String initials) {
//        return jdbcTemplate.query("""
//                        SELECT id,
//                        initials,
//                        date_of_birth
//                        FROM Person
//                        WHERE initials=?""", new Object[]{initials}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("""
//                INSERT INTO Person(initials, date_of_birth)
//                VALUES(?, ?)""", person.getInitials(), person.getDateOfBirth());
//    }
//
//    public void update(int id, Person updatePerson) {
//        jdbcTemplate.update("""
//                UPDATE Person
//                SET initials=?, date_of_birth=?
//                WHERE id=?""", updatePerson.getInitials(), updatePerson.getDateOfBirth(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("""
//                DELETE FROM Person
//                WHERE id=?""", id);
//    }
}
