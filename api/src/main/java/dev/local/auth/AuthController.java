package dev.local.auth;

import dev.local.dto.CreateUserDTO;
import dev.local.secruity.JwtAuthenticationRequest;
import dev.local.secruity.JwtAuthenticationResponse;
import dev.local.domain.User;
import dev.local.services.ProfileService;
import dev.local.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    private final AuthService authService;
    private final UserService userService;
    private final ProfileService profileService;

    @Autowired
    public AuthController(AuthService authService, UserService userService, ProfileService profileService){
        this.authService = authService;
        this.userService = userService;
        this.profileService = profileService;
    }

    @RequestMapping(value = "${jwt.route.authentication.login}", method = RequestMethod.POST)
    public Auth createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException{
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final User user = userService.findByUsername(authenticationRequest.getUsername());
        // Return the token
        return new Auth(token, user);
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody CreateUserDTO addedUser) throws AuthenticationException{
        User user = authService.register(addedUser.buildUser());
        if (user != null) {
            profileService.add(addedUser.buildProfile(), user.getId());
        }
        return user == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok(user);
    }
}
