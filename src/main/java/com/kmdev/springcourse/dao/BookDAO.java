package com.kmdev.springcourse.dao;

import com.kmdev.springcourse.models.Book;
import com.kmdev.springcourse.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookDAO {

//    private final JdbcTemplate jdbcTemplate;
//
//    public List<Book> index() {
//        return jdbcTemplate.query("""
//                SELECT id,
//                person_id,
//                name,
//                author,
//                year_of_publication
//                FROM Book""", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Book show(int id) {
//        Book book = new Book();
//        jdbcTemplate.query("""
//                SELECT
//                b.id AS b_id,
//                b.person_id AS b_p_id,
//                b.name,
//                b.author,
//                b.year_of_publication,
//                p.id AS p_id,
//                p.initials,
//                p.year_of_birth
//                FROM Book AS b
//                LEFT JOIN Person AS p
//                ON b.person_id = p.id
//                WHERE b.id=?""", new Object[]{id}, rs -> {
//
//            var bookId = rs.getInt("b_id");
//            if (bookId == 0) {
//                return;
//            }
//
//            book.setId(bookId);
//            book.setName(rs.getString("name"));
//            book.setAuthor(rs.getString("author"));
//            book.setYearOfPublication(rs.getInt("year_of_publication"));
//
//            Person person = new Person();
//            var personId = rs.getInt("b_p_id");
//            if (personId == 0) {
//                person = null;
//            } else {
//                person.setId(personId);
//                person.setInitials(rs.getString("initials"));
//                person.setDateOfBirth(rs.getInt("year_of_birth"));
//            }
//            book.setPerson(person);
//        });
//        return book.getId() == 0 ? null : book;
//    }
//
//    public Optional<Book> show(String name) {
//        return jdbcTemplate.query("""
//                        SELECT id AS id,
//                        person_id,
//                        name, author,
//                        year_of_publication
//                        FROM Book
//                        WHERE name=?""", new Object[]{name}, new BeanPropertyRowMapper<>(Book.class))
//                .stream().findAny();
//    }
//
//    public void addReader(int id, int humanId) {
//        jdbcTemplate.update("""
//                UPDATE Book
//                SET
//                person_id=?
//                WHERE id=?""", humanId, id);
//    }
//
//    public void releaseBook(int id) {
//        jdbcTemplate.update("""
//                UPDATE Book
//                SET
//                person_id=null
//                WHERE id=?""", id);
//    }
//
//    public void save(Book book) {
//        jdbcTemplate.update("""
//                        INSERT INTO Book(name, author, year_of_publication)
//                        VALUES(?, ?, ?)""", book.getName(),
//                book.getAuthor(), book.getYearOfPublication());
//    }
//
//    public void update(int id, Book updateBook) {
//        jdbcTemplate.update("""
//                        UPDATE Book
//                        SET name=?, author=?, year_of_publication=?
//                        WHERE id=?""",
//                updateBook.getName(), updateBook.getAuthor(), updateBook.getYearOfPublication(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("""
//                DELETE FROM Book
//                WHERE id=?""", id);
//    }
}
