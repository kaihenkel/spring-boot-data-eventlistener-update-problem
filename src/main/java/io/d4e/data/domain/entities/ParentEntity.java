package io.d4e.data.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Entity
@Table(name="parent")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ParentEntity {
    public static final UUID PRIMARY_KEY = UUID.fromString("1b649cc1-f150-47e7-84f0-9eacbb41ff7c");
    @Id
//    @GeneratedValue
    private UUID id = PRIMARY_KEY;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<ChildEntity> children;

    private String value;

    public ParentEntity setValue(String value) {
        this.value = value;
        // works when calling directly
//        updateChildren();
        return this;
    }
// Does not work on when using event-handlers
    @PreUpdate
    @PrePersist
    void updateChildren() {
        log.debug("+++++ update-children");
        if (children!=null) {
            children.forEach(ChildEntity::inheritValue);
        }
    }

    public void addChild(ChildEntity child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
        child.setParent(this);
    }
}
