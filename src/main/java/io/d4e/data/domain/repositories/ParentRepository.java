package io.d4e.data.domain.repositories;

import io.d4e.data.domain.entities.ParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParentRepository extends JpaRepository<ParentEntity, UUID> {
}
