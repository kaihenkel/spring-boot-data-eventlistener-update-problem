package io.d4e.data.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Entity
@Table(name = "eager_child")
@Data
@Slf4j
public class EagerChildEntity {
    public static final UUID PRIMARY_KEY = UUID.fromString("434cca6a-e094-4d88-af63-26e1f4257025");
    @Id
    private UUID id = PRIMARY_KEY;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private ParentEntity parent;

    private String value;

    public void update() {
        log.debug("+++++ inheriting value {} -> {}", this.value, this.parent.getValue());
        this.value = this.parent.getValue();
    }
}
