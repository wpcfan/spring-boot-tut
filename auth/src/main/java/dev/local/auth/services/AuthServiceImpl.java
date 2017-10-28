package dev.local.auth.services;

import dev.local.auth.domain.Authority;
import dev.local.auth.domain.AuthorityId;
import dev.local.auth.domain.User;
import dev.local.auth.dto.UserDTO;
import dev.local.auth.repositories.AuthorityRepo;
import dev.local.auth.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService {

    private final UserRepo repo;
    private final AuthorityRepo authRepo;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDTO register(UserDTO userDTO) throws HttpRequestMethodNotSupportedException{
        final String username = userDTO.getUsername();
        if(repo.findOne(username) != null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userDTO.getPassword();
        User user = new User()
                .withUsername(username)
                .withPassword(encoder.encode(rawPassword));
        User saved = repo.save(user);
        Authority savedAuth = authRepo.save(new Authority().withUser(saved).withId(new AuthorityId(username, "ROLE_USER")));
        return userDTO;
    }

    private void authenticateUser(UserDTO userDTO) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        // 进行安全认证
        final Authentication authentication = authenticationManager.authenticate(upToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
