package com.kmdev.springcourse.util;

import com.kmdev.springcourse.dto.PersonUpdateDto;
import com.kmdev.springcourse.models.Person;
import com.kmdev.springcourse.services.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
@RequiredArgsConstructor
public class PeopleUpdateValidator implements Validator {

    private final PeopleService peopleService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonUpdateDto personUpdateDto = (PersonUpdateDto) target;
        String dateString = personUpdateDto.getDateOfBirth();
        if (dateString == null || dateString.trim().isEmpty()) {
            errors.rejectValue("dateOfBirth", "dateOfBirth.empty", "Дата рождения не может быть пустой");
            return;
        }
        LocalDate parsedDate;
        try {
            parsedDate = DateUtils.parseLocalDate(dateString);
        } catch (DateTimeParseException e) {
            errors.rejectValue("dateOfBirth", "dateOfBirth.invalid", "Неверный формат даты. Используйте ГГГГ-ММ-ДД");
            return;
        }

        if (parsedDate.isAfter(LocalDate.now())) {
            errors.rejectValue("dateOfBirth", "dateOfBirth.future", "Дата рождения не может быть в будущем");
        }
        var humanFromDataBase = peopleService.findByInitials(personUpdateDto.getInitials());
        if (humanFromDataBase.isPresent() && humanFromDataBase.get().getId() != personUpdateDto.getId())
            errors.rejectValue("initials", "", "Человек с такими инциалами уже существует");
    }
}