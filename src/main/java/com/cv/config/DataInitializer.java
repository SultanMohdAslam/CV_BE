package com.cv.config;

import com.cv.service.CvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    private final CvService cvService;

    public DataInitializer(CvService cvService) {
        this.cvService = cvService;
    }

    @Override
    public void run(String... args) {
        log.info("Checking database data initialization...");
        try {
            cvService.seedInitialData(false);
            log.info("Database initialization completed successfully.");
        } catch (Exception e) {
            log.error("Failed to seed initial CV data: {}", e.getMessage(), e);
        }
    }
}
