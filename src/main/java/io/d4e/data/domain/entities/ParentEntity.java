package io.d4e.data.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Entity
@Table(name="parent")
@Data
@ToString(exclude = {"eagerChildren", "lazyChildren"})
public class ParentEntity {
    public static final UUID PRIMARY_KEY = UUID.fromString("1b649cc1-f150-47e7-84f0-9eacbb41ff7c");
    @Id
    private UUID id = PRIMARY_KEY;
    private UUID name = PRIMARY_KEY;
    private String value;

//    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EagerChildEntity> eagerChildren;

//    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LazyChildEntity> lazyChildren;


    @PreUpdate
    @PrePersist
    void updateChildren() {
        log.debug("+++++ update-children");
        // Does not work on when using event-handlers
        if (eagerChildren!=null) {
            eagerChildren.forEach(EagerChildEntity::update);
        }
        if (lazyChildren!=null) {
            lazyChildren.forEach(LazyChildEntity::update);
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
