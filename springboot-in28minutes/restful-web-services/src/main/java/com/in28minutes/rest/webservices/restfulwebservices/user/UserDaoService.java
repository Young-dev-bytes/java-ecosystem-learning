package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.ServletRequestBindingException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class UserDaoService {

    // JPA/Hibernate > Database
    // UserDaoService > Static List

    private final static List<User> users = new ArrayList<>();

    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20)));
    }


    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        /*Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();*/
        // return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        return users.stream().filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("id: " + id));
    }

    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public void deleteById(int id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
