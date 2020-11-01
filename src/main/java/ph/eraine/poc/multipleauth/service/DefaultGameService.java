package ph.eraine.poc.multipleauth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ph.eraine.poc.multipleauth.dto.CreateGame;
import ph.eraine.poc.multipleauth.dto.ViewGame;
import ph.eraine.poc.multipleauth.helper.PageHelper;
import ph.eraine.poc.multipleauth.model.Game;
import ph.eraine.poc.multipleauth.model.Tag;
import ph.eraine.poc.multipleauth.repository.GameRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class DefaultGameService implements GameService {

    private final GameRepository gameRepository;
    private final TagService tagService;

    @Override
    public Integer save(CreateGame request) {
        log.info("Saving new game: {}", request.toString());
        Game newGame = Game.builder()
                .name(request.getName())
                .build();

        request.getTags().forEach(tag -> {
            Tag existingTag = tagService.findByName(tag);
            if (Objects.isNull(existingTag)) {
                newGame.addTag(Tag.builder()
                        .name(tag)
                        .build());
            } else {
                newGame.addTag(existingTag);
            }
        });

        gameRepository.save(newGame);
        return newGame.getId();
    }

    public List<ViewGame> viewAll(Integer page, Integer limit) {
        Slice<ViewGame> games = gameRepository.findAll(PageHelper.getPageable(page, limit))
                .map(game -> ViewGame.builder()
                        .id(game.getId())
                        .name(game.getName())
                        .tags(game.getTags().stream()
                                .map(Tag::getName)
                                .collect(Collectors.toList()))
                        .build());

        log.info("Returning game list.");
        return games.getContent();
    }

}