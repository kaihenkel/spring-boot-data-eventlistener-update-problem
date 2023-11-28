package io.d4e.data.domain;

import io.d4e.data.domain.entities.ChildEntity;
import io.d4e.data.domain.entities.ParentEntity;
import io.d4e.data.domain.repositories.ParentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParentService {
    private final ParentRepository repository;


    private UUID parentId;

    @PostConstruct
    void init() {
        ParentEntity parent = new ParentEntity();
        parent.addChild(new ChildEntity());
        repository.save(parent);
        parentId = parent.getId();
    }

    @Transactional
    public void update(String value) {
        ParentEntity parent = repository.findById(parentId).orElseThrow(() -> new RuntimeException("Unknown Parent"));
        parent.setValue(value);
    }
}
