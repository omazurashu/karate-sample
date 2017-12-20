package org.om.karate.sample.resource;

import org.om.karate.sample.persistence.User;
import org.om.karate.sample.persistence.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Collection<User> getAll() {
        return repository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return repository.findOne(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return repository.save(user);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.delete(id);
    }
}
