package ph.eraine.poc.multipleauth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ViewGame {

    private Integer id;
    private String name;
    private List<String> tags;

}