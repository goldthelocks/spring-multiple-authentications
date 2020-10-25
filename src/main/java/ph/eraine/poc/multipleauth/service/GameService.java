package ph.eraine.poc.multipleauth.service;

import ph.eraine.poc.multipleauth.dto.CreateGame;
import ph.eraine.poc.multipleauth.dto.ViewGame;

import java.util.List;

public interface GameService {

    Integer save(CreateGame request);

    List<ViewGame> viewAll();

}