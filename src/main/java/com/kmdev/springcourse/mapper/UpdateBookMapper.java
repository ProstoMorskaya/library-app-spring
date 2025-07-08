package com.kmdev.springcourse.mapper;

import com.kmdev.springcourse.models.Book;
import org.springframework.stereotype.Component;

@Component
public class UpdateBookMapper {

    public void mapUpdateResults(Book bookFromDataBase, Book updatedBook) {
        bookFromDataBase.setName(updatedBook.getName());
        bookFromDataBase.setAuthor(updatedBook.getAuthor());
        bookFromDataBase.setYearOfPublication(updatedBook.getYearOfPublication());
    }
}
