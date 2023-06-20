package Filmex.api.controller;

import Filmex.api.domain.user.DataCreateUser;
import Filmex.api.domain.user.DataDetailingUser;
import Filmex.api.domain.user.User;
import Filmex.api.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid DataCreateUser data, UriComponentsBuilder uriBuilder) throws NoSuchAlgorithmException {
        var user = new User(data);
        System.out.println(data);
        repository.save(user);

        var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataDetailingUser(user));
    }
}
