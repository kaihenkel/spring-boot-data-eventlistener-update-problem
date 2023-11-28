package io.d4e.data.domain;

import io.d4e.data.domain.entities.ChildEntity;
import io.d4e.data.domain.entities.ParentEntity;
import io.d4e.data.domain.repositories.ParentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentService {
    private final ParentRepository repository;


    @PostConstruct
    void init() {
        log.debug("----- Init:start -----");
        ParentEntity parent = new ParentEntity();
        parent.addChild(new ChildEntity());
        repository.save(parent);
        log.debug("----- Init:end -----");
    }

    @Transactional
    public void update(String value) {
        log.debug("----- update:start -----");
        ParentEntity parent = repository.findById(ParentEntity.PRIMARY_KEY).orElseThrow(() -> new RuntimeException("Unknown Parent"));
        parent.setValue(value);
//        parent.getChildren().forEach(ChildEntity::inheritValue);
//        repository.save(parent);
        log.debug("----- update:end -----");
    }
}
