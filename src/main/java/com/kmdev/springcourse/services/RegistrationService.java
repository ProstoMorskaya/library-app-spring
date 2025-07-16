package com.kmdev.springcourse.services;

import com.kmdev.springcourse.dto.PersonCreateDto;
import com.kmdev.springcourse.mapper.CreatePersonMapper;
import com.kmdev.springcourse.models.Person;
import com.kmdev.springcourse.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final PeopleRepository peopleRepository;
    private final CreatePersonMapper createPersonMapper;

    @Transactional
    public void register(PersonCreateDto personCreateDto) {
        Person person = createPersonMapper.mapForm(personCreateDto);
        peopleRepository.save(person);
    }
}
