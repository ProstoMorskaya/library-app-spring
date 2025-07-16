package com.kmdev.springcourse.services;

import com.kmdev.springcourse.repositories.PeopleRepository;
import com.kmdev.springcourse.security.PersonDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var person = peopleRepository.findByInitials(username)
                .orElseThrow(() -> new UsernameNotFoundException("Человек с такими инициалами не найден"));
        return new PersonDetails(person);
    }
}
