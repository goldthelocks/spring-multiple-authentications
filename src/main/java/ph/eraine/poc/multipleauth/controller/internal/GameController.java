package ph.eraine.poc.multipleauth.controller.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.eraine.poc.multipleauth.dto.ApiResponse;
import ph.eraine.poc.multipleauth.dto.CreateGame;
import ph.eraine.poc.multipleauth.dto.ViewGame;
import ph.eraine.poc.multipleauth.service.GameService;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/v1/internal/games")
public class GameController {

    private final GameService service;

    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> save(@RequestBody CreateGame request) {
        // TODO - move request logging to filter
        log.info("Request received to save new game: {}", request.toString());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(service.save(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ViewGame>>> viewAll() {
        log.info("Request received to view all games.");
        return ResponseEntity.ok(new ApiResponse<>(service.viewAll()));
    }

}