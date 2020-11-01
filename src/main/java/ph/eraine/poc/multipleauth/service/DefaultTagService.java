package ph.eraine.poc.multipleauth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ph.eraine.poc.multipleauth.constant.ErrorCode;
import ph.eraine.poc.multipleauth.dto.CreateTag;
import ph.eraine.poc.multipleauth.dto.ViewTag;
import ph.eraine.poc.multipleauth.exception.ValidationException;
import ph.eraine.poc.multipleauth.helper.PageHelper;
import ph.eraine.poc.multipleauth.model.Tag;
import ph.eraine.poc.multipleauth.repository.TagRepository;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DefaultTagService implements TagService {

    private final TagRepository repository;

    @Override
    @Transactional
    public Integer save(CreateTag request) throws ValidationException {
        Integer existingTagCount = repository.countByNameIgnoreCase(request.getName());

        if (existingTagCount != 0) {
            log.info("Existing tag found: {}", request.getName());
            throw new ValidationException(ErrorCode.RES42201.name(), "Tag already exists.");
        }

        log.info("Saving new tag: {}", request.getName());
        Tag createdTag = repository.save(Tag.builder()
                .name(request.getName())
                .build());

        log.info("Created new tag: {}", createdTag.getId());
        return createdTag.getId();
    }

    @Override
    public Tag findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    @Override
    public List<ViewTag> viewAll(Integer page, Integer limit) {
        Slice<ViewTag> tags = repository.findAll(PageHelper.getPageable(page, limit))
                .map(tag -> ViewTag.builder()
                        .id(tag.getId())
                        .name(tag.getName())
                        .build());

        log.info("Returning tags.");
        return tags.getContent();
    }

}