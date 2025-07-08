package com.kmdev.springcourse.mapper;

import com.kmdev.springcourse.dto.PersonUpdateDto;
import com.kmdev.springcourse.models.Person;
import com.kmdev.springcourse.util.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class UpdatePersonMapper implements Mapper<PersonUpdateDto, Person> {
    @Override
    public Person mapForm(PersonUpdateDto object) {
        return Person.builder()
                .id(object.getId())
                .initials(object.getInitials())
                .dateOfBirth(DateUtils.parseLocalDate(object.getDateOfBirth()))
                .build();
    }
}
