package io.d4e.data.domain.repositories;

import io.d4e.data.domain.entities.EagerChildEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EagerChildRepository extends JpaRepository<EagerChildEntity, UUID> {
}
