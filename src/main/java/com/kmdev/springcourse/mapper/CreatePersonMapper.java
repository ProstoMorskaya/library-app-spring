package com.kmdev.springcourse.mapper;

import com.kmdev.springcourse.dto.PersonCreateDto;
import com.kmdev.springcourse.models.Person;
import com.kmdev.springcourse.models.Role;
import com.kmdev.springcourse.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePersonMapper implements Mapper<PersonCreateDto, Person> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Person mapForm(PersonCreateDto personCreateDto) {
        return Person.builder()
                .initials(personCreateDto.getInitials())
                .dateOfBirth(DateUtils.parseLocalDate(personCreateDto.getDateOfBirth()))
                .password(passwordEncoder.encode(personCreateDto.getPassword()))
                .role(Role.USER)
                .build();
    }
}
