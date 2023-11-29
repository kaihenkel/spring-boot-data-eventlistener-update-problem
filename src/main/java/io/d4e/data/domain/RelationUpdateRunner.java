package io.d4e.data.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Profile("default")
public class RelationUpdateRunner implements CommandLineRunner {

    private final ParentService service;

    @Override
    public void run(String... args) throws Exception {
        log.debug("----- START -----");
        service.updateById("value");
        log.debug("-----  END  -----");
    }
}
