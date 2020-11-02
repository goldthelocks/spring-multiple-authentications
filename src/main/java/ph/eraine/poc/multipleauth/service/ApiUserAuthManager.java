package ph.eraine.poc.multipleauth.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ph.eraine.poc.multipleauth.model.security.UserAuthenticationToken;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ApiUserAuthManager implements AuthenticationManager {

    private static final int USERNAME_INDEX = 0;
    private static final int ROLES_INDEX = 2;
    private static final int ID_TOKEN_LENGTH = 3;
    private static final String ID_DELIMITER = ";";
    private static final String ROLE_DELIMITER = ",";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = (String) authentication.getPrincipal();

        if (StringUtils.isBlank(userId)) {
            log.info("Invalid credentials.");
            throw new BadCredentialsException("Invalid credentials.");
        }

        String[] tokens = userId.split(ID_DELIMITER);

        if (tokens.length != ID_TOKEN_LENGTH) {
            log.info("Invalid credentials.");
            throw new BadCredentialsException("Invalid credentials.");
        }

        return new UserAuthenticationToken(tokens[USERNAME_INDEX],
                createGrantedAuthorities(getRoles(tokens[ROLES_INDEX])));
    }

    private List<? extends GrantedAuthority> createGrantedAuthorities(Set<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private Set<String> getRoles(String role) {
        return Arrays.stream(role.split(ROLE_DELIMITER))
                .collect(Collectors.toSet());
    }

}