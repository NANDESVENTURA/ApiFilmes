package Filmex.api.controller;

import Filmex.api.domain.user.DataCreateUser;
import Filmex.api.domain.user.DataDetailingUser;
import Filmex.api.domain.user.User;
import Filmex.api.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid DataCreateUser data, UriComponentsBuilder uriBuilder) throws NoSuchAlgorithmException {
        try {
            UserDetails userExists = repository.findByEmail(data.email());
            if (userExists != null) {
                HashMap<String, String> ErrorMessage = new HashMap<>();
                ErrorMessage.put("ErrorMessage", "Email already registered");
                return ResponseEntity.badRequest().body(ErrorMessage);
            }
            var user = new User(data);
            System.out.println(data);
            repository.save(user);

            var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(uri).body(new DataDetailingUser(user));
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Internal Error - Could not complete", "Internal Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }
}