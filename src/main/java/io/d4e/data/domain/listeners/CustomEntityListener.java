package io.d4e.data.domain.listeners;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomEntityListener {

    @PrePersist
    public void prePersist(Object target) {
        log.debug("+++++ prePersist {}", target);
    }
    @PreUpdate
    public void preUpdate(Object target) {
        log.debug("+++++ preUpdate {}", target);
    }
}
