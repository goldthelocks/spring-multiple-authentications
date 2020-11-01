package ph.eraine.poc.multipleauth.service;

import ph.eraine.poc.multipleauth.dto.CreateTag;
import ph.eraine.poc.multipleauth.dto.ViewTag;
import ph.eraine.poc.multipleauth.exception.ValidationException;
import ph.eraine.poc.multipleauth.model.Tag;

import java.util.List;

public interface TagService {

    Integer save(CreateTag request) throws ValidationException;

    Tag findByName(String name);

    List<ViewTag> viewAll(Integer page, Integer limit);

}