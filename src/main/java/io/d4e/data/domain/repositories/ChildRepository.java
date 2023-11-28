package io.d4e.data.domain.repositories;

import io.d4e.data.domain.entities.ChildEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChildRepository extends JpaRepository<ChildEntity, UUID> {
}
