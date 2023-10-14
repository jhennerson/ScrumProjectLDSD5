package com.ifsp.scrumProjectLDSD5.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ifsp.scrumProjectLDSD5.dto.UserStoryDTO;
import com.ifsp.scrumProjectLDSD5.exception.RecordNotFoundException;
import com.ifsp.scrumProjectLDSD5.mapper.UserStoryMapper;
import com.ifsp.scrumProjectLDSD5.repository.UserStoryRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class UserStoryService {
    private final UserStoryRepository userStoryRepository;
    private final UserStoryMapper userStoryMapper;

    public UserStoryService(UserStoryRepository userStoryRepository, UserStoryMapper userStoryMapper) {
        this.userStoryRepository = userStoryRepository;
        this.userStoryMapper = userStoryMapper;
    }

    public List<UserStoryDTO> list() {
        return userStoryRepository.findAll()
                                  .stream()
                                  .map(userStoryMapper::toDTO)
                                  .collect(Collectors.toList());
    }

    public UserStoryDTO findById(@PathVariable @NotNull @Positive Long id) {
        return userStoryRepository.findById(id)
                                  .map(userStoryMapper::toDTO)
                                  .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public UserStoryDTO create(@Valid @NotNull UserStoryDTO userStory) {
        return userStoryMapper.toDTO(userStoryRepository.save(userStoryMapper.toEntity(userStory)));
    }

    public UserStoryDTO update(@NotNull @Positive Long id, @Valid @NotNull UserStoryDTO userStory) {
        return userStoryRepository.findById(id)
                                    .map(recordFound -> {
                                    recordFound.setTitle(userStory.title());
                                    recordFound.setAssignee(userStory.assignee());
                                    recordFound.setReporter(userStory.reporter());
                                    recordFound.setDescription(userStory.description());

                                    return userStoryMapper.toDTO(userStoryRepository.save(recordFound));
                                  }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        userStoryRepository.delete(userStoryRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
