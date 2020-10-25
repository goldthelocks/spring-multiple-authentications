package ph.eraine.poc.multipleauth.config;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ErrorHandlingConfig {

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {

            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                Map<String, Object> currentAttributeMap = super.getErrorAttributes(webRequest, options);
                Map<String, Object> newAttributeMap = new LinkedHashMap<>();
                newAttributeMap.put("message", currentAttributeMap.get("message"));
                newAttributeMap.put("timestamp", OffsetDateTime.now());
                return newAttributeMap;
            }
        };
    }

}