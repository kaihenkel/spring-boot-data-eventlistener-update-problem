package io.d4e.data.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
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

    @OneToMany(mappedBy = "parent")
    private List<ChildEntity> children;
}
