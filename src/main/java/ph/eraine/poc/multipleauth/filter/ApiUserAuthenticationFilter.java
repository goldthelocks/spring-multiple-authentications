package ph.eraine.poc.multipleauth.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class ApiUserAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader("x-authenticated-userid");
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return StringUtils.EMPTY;
    }

}