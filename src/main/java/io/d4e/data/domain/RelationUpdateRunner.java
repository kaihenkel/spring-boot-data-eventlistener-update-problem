package io.d4e.data.domain;

import io.d4e.data.domain.entities.ChildEntity;
import io.d4e.data.domain.entities.ParentEntity;
import io.d4e.data.domain.repositories.ParentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RelationUpdateRunner implements CommandLineRunner {

    private final ParentService service;

    @Override
    public void run(String... args) throws Exception {
        log.debug("----- START -----");
        service.update("value");
        log.debug("-----  END  -----");
    }
}
