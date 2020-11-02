package ph.eraine.poc.multipleauth.model.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import ph.eraine.poc.multipleauth.model.User;

import java.util.Collection;

public class UserAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    public UserAuthenticationToken(String token) {
        super(null);

        this.principal = token;
        setAuthenticated(false);
    }

    public UserAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return null;
    }

    public Object getPrincipal() {
        return null;
    }

}