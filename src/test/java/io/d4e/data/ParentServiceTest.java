package io.d4e.data;

import io.d4e.data.domain.ParentService;
import io.d4e.data.domain.entities.EagerChildEntity;
import io.d4e.data.domain.entities.LazyChildEntity;
import io.d4e.data.domain.repositories.EagerChildRepository;
import io.d4e.data.domain.repositories.LazyChildRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class ParentServiceTest {

    @Autowired
    private ParentService service;

    @Autowired
    private EagerChildRepository eagerChildRepository;

    @Autowired
    private LazyChildRepository lazyChildRepository;

    @Test
    void testUpdateById_EagerChildCheck() {
        log.debug("----- test: start");
        String value = "testUpdateById_EagerChildCheck";

        log.debug("----- test:update: start");
        service.updateById(value);
        log.debug("----- test:update: complete");

        log.debug("----- test:fetch-child: start");
        EagerChildEntity actual = eagerChildRepository.findById(EagerChildEntity.PRIMARY_KEY).orElseThrow(() -> new RuntimeException("Child Entity not found"));
        log.debug("----- test:fetch-child: complete");
        assertThat(actual.getValue(), equalTo(value));
        log.debug("----- test: complete");
    }

    @Test
    void testupdateByName_EagerChildCheck() {
        log.debug("----- test: start");
        String value = "testupdateByName_EagerChildCheck";

        log.debug("----- test:update: start");
        service.updateByName(value);
        log.debug("----- test:update: complete");

        log.debug("----- test:fetch-child: start");
        EagerChildEntity actual = eagerChildRepository.findById(EagerChildEntity.PRIMARY_KEY).orElseThrow(() -> new RuntimeException("Child Entity not found"));
        log.debug("----- test:fetch-child: complete");
        assertThat(actual.getValue(), equalTo(value));
        log.debug("----- test: complete");
    }

    @Test
    void testUpdateById_LazyChildCheck() {
        log.debug("----- test: start");
        String value = "testUpdateById_LazyChildCheck";

        log.debug("----- test:update: start");
        service.updateById(value);
        log.debug("----- test:update: complete");

        log.debug("----- test:fetch-child: start");
        LazyChildEntity actual = lazyChildRepository.findById(LazyChildEntity.PRIMARY_KEY).orElseThrow(() -> new RuntimeException("Child Entity not found"));
        log.debug("----- test:fetch-child: complete");
        assertThat(actual.getValue(), equalTo(value));
        log.debug("----- test: complete");
    }

    @Test
    void testupdateByName_LazyChildCheck() {
        log.debug("----- test: start");
        String value = "testupdateByName_LazyChildCheck";

        log.debug("----- test:update: start");
        service.updateByName(value);
        log.debug("----- test:update: complete");

        log.debug("----- test:fetch-child: start");
        LazyChildEntity actual = lazyChildRepository.findById(LazyChildEntity.PRIMARY_KEY).orElseThrow(() -> new RuntimeException("Child Entity not found"));
        log.debug("----- test:fetch-child: complete");
        assertThat(actual.getValue(), equalTo(value));
        log.debug("----- test: complete");
    }
}
