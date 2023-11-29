package io.d4e.data.domain.entities;

import io.d4e.data.domain.listeners.CustomEntityListener;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "eager_child")
@EntityListeners({AuditingEntityListener.class, CustomEntityListener.class})
@Data
@Slf4j
public class EagerChildEntity {
    public static final UUID PRIMARY_KEY = UUID.fromString("434cca6a-e094-4d88-af63-26e1f4257025");
    @Id
    private UUID id = PRIMARY_KEY;
    @ManyToOne
    @JoinColumn(name="parent_id")
    private ParentEntity parent;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private String value;

    @PrePersist
    @PreUpdate
    public void inheritValue() {
        log.debug("+++++ inheriting value {} -> {}", this.value, this.parent.getValue());
        this.value = this.parent.getValue();
    }

}
