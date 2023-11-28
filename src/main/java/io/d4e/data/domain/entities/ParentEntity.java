package io.d4e.data.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="parent")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ParentEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "parent", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<ChildEntity> children;

    private String value;

    public ParentEntity setValue(String value) {
        this.value = value;
        giveValueToChildren();
        return this;
    }
//    @PrePersist
//    @PreUpdate
    void giveValueToChildren() {
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
