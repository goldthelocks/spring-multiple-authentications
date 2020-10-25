package ph.eraine.poc.multipleauth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ph.eraine.poc.multipleauth.model.Role;
import ph.eraine.poc.multipleauth.model.User;
import ph.eraine.poc.multipleauth.model.security.UserAuthenticationToken;
import ph.eraine.poc.multipleauth.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class InternalUserAuthProvider implements AuthenticationProvider {

    private final UserRepository repository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        log.info("Authenticating user: {}", username);

        User user = repository.findByUsernameIgnoreCase(username);

        if (Objects.isNull(user) || !BCrypt.checkpw(password, user.getPassword())) {
            log.info("Invalid credentials.");
            throw new BadCredentialsException("Invalid credentials.");
        }

        return new UserAuthenticationToken(username, createGrantedAuthorities(user.getRoles()));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<? extends GrantedAuthority> createGrantedAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}