package com.kmdev.springcourse.mapper;

import com.kmdev.springcourse.dto.PersonCreateDto;
import com.kmdev.springcourse.models.Person;
import com.kmdev.springcourse.util.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class CreatePersonMapper implements Mapper<PersonCreateDto, Person> {

    @Override
    public Person mapForm(PersonCreateDto object) {
        return Person.builder()
                .initials(object.getInitials())
                .dateOfBirth(DateUtils.parseLocalDate(object.getDateOfBirth()))
                .build();
    }
}
