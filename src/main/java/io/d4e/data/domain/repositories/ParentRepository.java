package io.d4e.data.domain.repositories;

import io.d4e.data.domain.entities.ParentEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ParentRepository extends JpaRepository<ParentEntity, UUID> {
    @Override
    //@EntityGraph(attributePaths = {"lazyChildren"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<ParentEntity> findById(UUID uuid);

    //@EntityGraph(attributePaths = {"lazyChildren"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<ParentEntity> findByName(UUID name);
}
