package io.d4e.data.domain;

import io.d4e.data.domain.entities.EagerChildEntity;
import io.d4e.data.domain.entities.LazyChildEntity;
import io.d4e.data.domain.entities.ParentEntity;
import io.d4e.data.domain.repositories.ParentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentService {
    private final ParentRepository repository;


    @PostConstruct
    void init() {
        log.debug("----- Init:start -----");
        ParentEntity parent = new ParentEntity();
        parent.addEagerChild(new EagerChildEntity());
        parent.addLazyChild(new LazyChildEntity());
        repository.save(parent);
        log.debug("----- Init:end -----");
    }

    @Transactional
    public void updateById(String value) {
        log.debug("----- update:start -----");
        ParentEntity parent = repository.findById(ParentEntity.PRIMARY_KEY).orElseThrow(() -> new RuntimeException("Unknown Parent"));
        parent.setValue(value);
        log.debug("----- update:end -----");
    }

    @Transactional
    public void updateByName(String value) {
        log.debug("----- update:start -----");
        ParentEntity parent = repository.findByName(ParentEntity.PRIMARY_KEY).orElseThrow(() -> new RuntimeException("Unknown Parent"));
        parent.setValue(value);
        log.debug("----- update:end -----");
    }
}
