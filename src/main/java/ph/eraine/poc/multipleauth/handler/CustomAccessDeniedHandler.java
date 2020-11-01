package ph.eraine.poc.multipleauth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import ph.eraine.poc.multipleauth.constant.ErrorCode;
import ph.eraine.poc.multipleauth.dto.ApiErrorDetail;
import ph.eraine.poc.multipleauth.dto.ApiErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException exception) throws IOException {
        ApiErrorResponse apiResponse = new ApiErrorResponse();
        apiResponse.add(new ApiErrorDetail(ErrorCode.RES40301.name(), exception.getLocalizedMessage()));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }

}