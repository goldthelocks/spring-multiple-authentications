package ph.eraine.poc.multipleauth.controller.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ph.eraine.poc.multipleauth.dto.ApiResponse;
import ph.eraine.poc.multipleauth.dto.CreateTag;
import ph.eraine.poc.multipleauth.dto.ViewGame;
import ph.eraine.poc.multipleauth.dto.ViewTag;
import ph.eraine.poc.multipleauth.exception.ValidationException;
import ph.eraine.poc.multipleauth.service.TagService;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/v1/internal/tags")
public class TagController {

    private final TagService service;

    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> save(@RequestBody CreateTag request) throws ValidationException {
        // TODO: move request logging to filter
        log.info("Request received to save new tag: {}", request.toString());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(service.save(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ViewTag>>> viewAll(@RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit) {
        log.info("Request received to view tags. [page: {}] [limit: {}]", page, limit);
        return ResponseEntity.ok(new ApiResponse<>(service.viewAll(page, limit)));
    }

}