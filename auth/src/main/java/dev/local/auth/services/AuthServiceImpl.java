package dev.local.auth.services;

import dev.local.auth.domain.Authority;
import dev.local.auth.domain.AuthorityId;
import dev.local.auth.domain.User;
import dev.local.auth.dto.RegisterUserDTO;
import dev.local.auth.exceptions.UsernameTakenException;
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
    public RegisterUserDTO register(RegisterUserDTO userDTO) throws HttpRequestMethodNotSupportedException{
        final String username = userDTO.getUsername();
        if(repo.findOne(username) != null) {
            throw new UsernameTakenException("Username has been taken");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userDTO.getPassword();
        User user = User.builder()
                .username(username)
                .password(encoder.encode(rawPassword)).build();
        User saved = repo.save(user);
        Authority savedAuth = authRepo.save(
                Authority.builder()
                        .user(saved)
                        .id(AuthorityId.builder()
                                .username(username)
                                .authority("ROLE_USER")
                                .build())
                        .build());
        return userDTO;
    }

    private void authenticateUser(RegisterUserDTO userDTO) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        // 进行安全认证
        final Authentication authentication = authenticationManager.authenticate(upToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
