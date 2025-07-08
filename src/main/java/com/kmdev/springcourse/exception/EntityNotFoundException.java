package com.kmdev.springcourse.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Сущность с таким id=%d не найдена";

    public EntityNotFoundException(int id) {
        super(String.format(MESSAGE, id));
    }
}
