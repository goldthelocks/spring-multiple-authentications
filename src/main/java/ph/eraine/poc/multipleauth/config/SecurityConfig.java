package ph.eraine.poc.multipleauth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ph.eraine.poc.multipleauth.handler.CustomAccessDeniedHandler;
import ph.eraine.poc.multipleauth.handler.CustomAuthenticationEntryPoint;
import ph.eraine.poc.multipleauth.service.InternalUserAuthProvider;

@EnableWebSecurity
public class SecurityConfig {

    @RequiredArgsConstructor
    @Configuration
    @Order(1)
        public static class InternalSecurityConfig extends WebSecurityConfigurerAdapter {

        private final InternalUserAuthProvider userAuthProvider;
        private final CustomAccessDeniedHandler accessDeniedHandler;
        private final CustomAuthenticationEntryPoint authenticationEntryPoint;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(userAuthProvider);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            /* This intercepts all requests with /v1/internal/** prefix and authenticates them using basic auth. */
            http
                    .csrf().disable()
                    .formLogin().disable()
                    .antMatcher("/v1/internal/**")
                    .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler)
                    .and()
                    .httpBasic()
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/v1/internal/games")
                    .hasAnyAuthority(InternalUserRole.SUPER_ADMIN, InternalUserRole.CONTENT_IMPORTER)
                    .antMatchers("/v1/internal/**").hasAuthority(InternalUserRole.SUPER_ADMIN)
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

    }

    @RequiredArgsConstructor
    @Configuration
    public static class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            super.configure(auth);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            super.configure(http);
        }

        @Override
        public void configure(WebSecurity web) {
            web.ignoring()
                    .antMatchers("/h2-console/**");
        }
    }

    private static class InternalUserRole {

        public static final String SUPER_ADMIN = "SUPER_ADMIN";
        public static final String CONTENT_IMPORTER = "CONTENT_IMPORTER";

    }

}