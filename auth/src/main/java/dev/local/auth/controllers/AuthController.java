package dev.local.auth.controllers;

import dev.local.auth.dto.UserDTO;
import dev.local.auth.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final AuthService service;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserDTO register(@RequestBody UserDTO user) throws HttpRequestMethodNotSupportedException {
        return service.register(user);
    }
}
