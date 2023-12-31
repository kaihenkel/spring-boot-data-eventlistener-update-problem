package io.d4e.data.domain.repositories;

import io.d4e.data.domain.entities.LazyChildEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LazyChildRepository extends JpaRepository<LazyChildEntity, UUID> {
}
