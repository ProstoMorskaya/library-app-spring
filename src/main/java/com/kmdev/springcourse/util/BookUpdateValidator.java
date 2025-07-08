package com.kmdev.springcourse.util;

import com.kmdev.springcourse.models.Book;
import com.kmdev.springcourse.services.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class BookUpdateValidator implements Validator {

    private final BooksService booksService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        var bookFromDataBase = booksService.findByName(book.getName());
        if (bookFromDataBase.isPresent() && bookFromDataBase.get().getId() != book.getId()) {
            errors.rejectValue("name", "", "Книга с таким названием уже существует");
        }
    }
}
