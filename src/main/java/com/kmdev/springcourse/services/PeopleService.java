package com.kmdev.springcourse.services;

import com.kmdev.springcourse.dto.PersonCreateDto;
import com.kmdev.springcourse.dto.PersonUpdateDto;
import com.kmdev.springcourse.exception.EntityNotFoundException;
import com.kmdev.springcourse.mapper.CreatePersonMapper;
import com.kmdev.springcourse.mapper.UpdatePersonMapper;
import com.kmdev.springcourse.models.Person;
import com.kmdev.springcourse.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final CreatePersonMapper createPersonMapper;
    private final UpdatePersonMapper updatePersonMapper;

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(int id) {
        return peopleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    public Person findByIdWithBooks(int id) {
        return peopleRepository.findByIdWithBooks(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }


    public Optional<Person> findByInitials(String initials) {
        return peopleRepository.findByInitials(initials);
    }

    @Transactional
    public void save(PersonCreateDto personCreateDto) {
        Person person = createPersonMapper.mapForm(personCreateDto);
        peopleRepository.save(person);
    }

    @Transactional
    public void update(PersonUpdateDto personUpdateDto) {
        var updatePerson = updatePersonMapper.mapForm(personUpdateDto);
        peopleRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
