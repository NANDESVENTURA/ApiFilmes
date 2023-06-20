package Filmex.api.controller;

import Filmex.api.domain.user.DataAuthentication;
import Filmex.api.domain.user.User;
import Filmex.api.infra.security.DataTokenJWT;
import Filmex.api.infra.security.TokenService;
import Filmex.api.utils.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataAuthentication data) throws NoSuchAlgorithmException {
//        String password = new SecurityUtils().crypt(data.password());
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.name(), data.password());
        System.out.println(authenticationToken);
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }
}
