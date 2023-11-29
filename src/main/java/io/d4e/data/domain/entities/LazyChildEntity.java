package io.d4e.data.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Entity
@Table(name = "lazy_child")
@Data
@Slf4j
public class LazyChildEntity {
    public static final UUID PRIMARY_KEY = UUID.fromString("812c3d6d-ea24-4755-ac0e-38a9ec7f60b7");
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
