package ph.eraine.poc.multipleauth.helper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

public final class PageHelper {

    public static Pageable getPageable(Integer page, Integer limit) {
        if (Objects.isNull(page) || Objects.isNull(limit)) {
            return PageRequest.of(0, 10);
        }
        return PageRequest.of(page, limit);
    }

}