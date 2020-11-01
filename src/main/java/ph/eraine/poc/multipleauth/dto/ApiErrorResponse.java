package ph.eraine.poc.multipleauth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApiErrorResponse {

    private List<ApiErrorDetail> errors = new ArrayList<>();

    public void add(ApiErrorDetail error) {
        errors.add(error);
    }

}