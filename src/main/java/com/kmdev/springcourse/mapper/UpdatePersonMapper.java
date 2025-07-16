package com.kmdev.springcourse.mapper;

import com.kmdev.springcourse.dto.PersonUpdateDto;
import com.kmdev.springcourse.models.Person;
import com.kmdev.springcourse.models.Role;
import com.kmdev.springcourse.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdatePersonMapper implements Mapper<PersonUpdateDto, Person> {

    private final PasswordEncoder passwordEncoder;
    @Override
    public Person mapForm(PersonUpdateDto personUpdateDto) {
        return Person.builder()
                .id(personUpdateDto.getId())
                .initials(personUpdateDto.getInitials())
                .dateOfBirth(DateUtils.parseLocalDate(personUpdateDto.getDateOfBirth()))
                .password(passwordEncoder.encode(personUpdateDto.getPassword()))
                .role(Role.valueOf(personUpdateDto.getRole()))
                .build();
    }
}
