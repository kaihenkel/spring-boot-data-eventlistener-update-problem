package io.d4e.data.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
@ToString(exclude = {"eagerChildren", "lazyChildren"})
public class ParentEntity {
    public static final UUID PRIMARY_KEY = UUID.fromString("1b649cc1-f150-47e7-84f0-9eacbb41ff7c");
    @Id
    private UUID id = PRIMARY_KEY;

    private UUID name = PRIMARY_KEY;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    //@Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EagerChildEntity> eagerChildren;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LazyChildEntity> lazyChildren;

    private String value;

    public ParentEntity setValue(String value) {
        this.value = value;
        // works when calling directly
        // updateChildren();
        return this;
    }
    @PreUpdate
    @PrePersist
    void updateChildren() {
        log.debug("+++++ update-children");
        // Does not work on when using event-handlers
        if (eagerChildren!=null) {
            eagerChildren.forEach(EagerChildEntity::inheritValue);
        }
        if (lazyChildren!=null) {
            lazyChildren.forEach(LazyChildEntity::inheritValue);
        }
    }

    public void addEagerChild(EagerChildEntity eagerChild) {
        if (eagerChildren == null) {
            eagerChildren = new ArrayList<>();
        }
        eagerChildren.add(eagerChild);
        eagerChild.setParent(this);
    }

    public void addLazyChild(LazyChildEntity lazyChild) {
        if (lazyChildren == null) {
            lazyChildren = new ArrayList<>();
        }
        lazyChildren.add(lazyChild);
        lazyChild.setParent(this);
    }
}
