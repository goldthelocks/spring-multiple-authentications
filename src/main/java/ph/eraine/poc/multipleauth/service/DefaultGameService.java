package ph.eraine.poc.multipleauth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ph.eraine.poc.multipleauth.dto.CreateGame;
import ph.eraine.poc.multipleauth.dto.ViewGame;
import ph.eraine.poc.multipleauth.model.Game;
import ph.eraine.poc.multipleauth.repository.GameRepository;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class DefaultGameService implements GameService {

    private final GameRepository repository;

    @Override
    public Integer save(CreateGame request) {
        log.info("Saving new game: {}", request.toString());
        Game createdGame = repository.save(Game.builder()
                .name(request.getName())
                .build());

        return createdGame.getId();
    }

    public List<ViewGame> viewAll() {
        List<Game> games = repository.findAll();
        log.info("Found {} games", games.size());
        return games.stream()
                .map(game -> ViewGame.builder()
                        .id(game.getId())
                        .name(game.getName())
                        .build())
                .collect(Collectors.toList());
    }

}