package io.d4e.data;

import io.d4e.data.domain.ParentService;
import io.d4e.data.domain.entities.ChildEntity;
import io.d4e.data.domain.repositories.ChildRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UpdateByNameFirstTest {

    @Autowired
    private ParentService service;

    @Autowired
    private ChildRepository childRepository;

    @Test
    @Order(2)
    void testUpdateById() {
        log.debug("----- test: start");
        String value = "some-value";

        log.debug("----- test:update: start");
        service.updateById(value);
        log.debug("----- test:update: complete");

        log.debug("----- test:fetch-child: start");
        ChildEntity actual = childRepository.findById(ChildEntity.PRIMARY_KEY).orElseThrow(() -> new RuntimeException("Child Entity not found"));
        log.debug("----- test:fetch-child: complete");
        assertThat(actual.getValue(), equalTo(value));
        log.debug("----- test: complete");
    }

    @Test
    @Order(1)
    void testupdateByName() {
        log.debug("----- test: start");
        String value = "some-value";

        log.debug("----- test:update: start");
        service.updateByName(value);
        log.debug("----- test:update: complete");

        log.debug("----- test:fetch-child: start");
        ChildEntity actual = childRepository.findById(ChildEntity.PRIMARY_KEY).orElseThrow(() -> new RuntimeException("Child Entity not found"));
        log.debug("----- test:fetch-child: complete");
        assertThat(actual.getValue(), equalTo(value));
        log.debug("----- test: complete");
    }
}
