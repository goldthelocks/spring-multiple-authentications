package ph.eraine.poc.multipleauth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ph.eraine.poc.multipleauth.constant.ErrorCode;
import ph.eraine.poc.multipleauth.dto.ApiErrorDetail;
import ph.eraine.poc.multipleauth.dto.ApiErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        ApiErrorResponse apiResponse = new ApiErrorResponse();
        apiResponse.add(new ApiErrorDetail(ErrorCode.RES40101.name(), authException.getLocalizedMessage()));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }

}