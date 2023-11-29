# Spring-Boot Data Example Project

This Example Project demonstrates a Problem when updating related entities 
in the Jakarta Event-Listener callback @PreUpdate. 
The updated related entities are not persisted when the relation is not accessed during 
the transaction that triggers the update event.

## General Problem

We have to entities, Parent and Child with a one-to-many relationship between them.
When updating the Parent we want to update some entries in all related children automatically.

For sake of simplicity we are storing the changed property (value) in the child-entity as well.
The actual problem involves changing different properties and calculating a new property 
in the related children based on those changes.

Getter/Setter ToString... are omitted for simplicity

    @Entity
    public class ParentEntity {
        @Id
        @GeneratedValue
        private UUID id;
        private String name;
        private String value;

        @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
        private List<ChildEntity> children;
    }

    @Entity
    public class ChildEntity {
        @Id
        @GeneratedValue
        private UUID id;
    
        @ManyToOne
        @JoinColumn(name="parent_id")
        private ParentEntity parent;

        private String value;
    }


## Used Approach - Event-Listener

Since the changes are done from different services and methods we are looking for a
centralized solution, so we decided to use Event-Listeners 
in the Parent that triggers the update process in all its children. 

Only additional methods are shown

    public class ParentEntity {

        @PrePersist
        @PreUpdate
        void onUpdate() {
            if (children != null) {
                this.children.forEach(ChildEntity::update)
            }
        }
    }

    public class ChildEntity {
        void update() {
            if (parent != null) {
                this.value = parent.getValue();
            }
        }
    }

## Problem

This approach does not always update the child-entity when changing the parent-entity value.

We identified multiple factors that influence the outcome

- @OneToMany annotation on ParentEntity#children with fetch eager/lazy 
- Hibernate-Specific annotation @Fetch on ParentEntity#children with JOIN/SELECT/SUBSELECT
- Accessing the children list within the same transaction as the update
- spring specific: use repository findById or custom findByName

We also tried @EntityGraph annotations but discarded the approach
since it would be to access-dependent wich negates the "one method" approach.

calling the repository-save method within the transaction does not have any effect.

This results in the following success/failed matrix's

+: success. -: failed

@OneToMan fetch **LAZY** and @Fetch mode

|                         | no-annotation | JOIN | SELECT or SUBSELECT |
|-------------------------|---------------|------|---------------------|
| find by id              | -             | +    | -                   |
| find by id and access   | +             | +    | +                   |
| find by name            | -             | +    | -                   |
| find by name and access | +             | +    | +                   |

@OneToMan fetch **EAGER** and @Fetch mode

|                         | no-annotation | JOIN | SELECT or SUBSELECT |
|-------------------------|---------------|------|---------------------|
| find by id              | -             | -    | -                   |
| find by id and access   | -             | -    | +                   |
| find by name            | +             | +    | -                   |
| find by name and access | +             | +    | +                   |

# Open Questions

- Why do repository calls by findById and findByName have different results?
- Use wich combination of Annotations? 
- Is there an entirely different approach?