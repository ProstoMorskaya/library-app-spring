package com.kmdev.springcourse.mapper;

public interface Mapper<F, T> {
    T mapForm(F object);
}