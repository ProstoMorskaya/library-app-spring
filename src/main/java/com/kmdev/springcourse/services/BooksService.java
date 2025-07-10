package com.kmdev.springcourse.services;

import com.kmdev.springcourse.exception.EntityNotFoundException;
import com.kmdev.springcourse.mapper.UpdateBookMapper;
import com.kmdev.springcourse.models.Book;
import com.kmdev.springcourse.repositories.BooksRepository;
import com.kmdev.springcourse.repositories.PeopleRepository;
import com.kmdev.springcourse.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;
    private final UpdateBookMapper updateBookMapper;

    public List<Book> findAll() {
       return booksRepository.findAll();
    }

    public Page<Book> findAll(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }


    public Book findById(int id) {
        return booksRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    public Book findByIdWithPerson(int id) {
        return booksRepository.findByIdWithPerson(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    public Optional<Book> findByName(String name) {
        return booksRepository.findByName(name);
    }

    public List<Book> searchBooksByPrefix(String prefix) {
        Specification<Book> spec = BookSpecification.nameStartWith(prefix);
        return booksRepository.findAll(spec);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {
        var book = findById(id);
        updateBookMapper.mapUpdateResults(book, updateBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void addReader(int id, int personId) {
        var book = booksRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        var person = peopleRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException(id));
        book.setPerson(person);
        book.setTakenAt(LocalDateTime.now());
    }

    @Transactional
    public void releaseBook(int id) {
        var book = booksRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        book.setPerson(null);
        book.setTakenAt(null);
    }
}
