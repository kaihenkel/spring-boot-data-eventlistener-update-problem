package io.d4e.data.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RelationUpdateRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.debug("----- START -----");
        log.debug("-----  END  -----");
    }
}
