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
    private final com.cv.service.AdminSecurityService adminSecurityService;

    public DataInitializer(CvService cvService, com.cv.service.AdminSecurityService adminSecurityService) {
        this.cvService = cvService;
        this.adminSecurityService = adminSecurityService;
    }

    @Override
    public void run(String... args) {
        log.info("Checking database data initialization...");
        try {
            adminSecurityService.initializeDefaultPasscode();
            cvService.seedInitialData(false);
            log.info("Database initialization completed successfully.");
        } catch (Exception e) {
            log.error("Failed to seed initial CV data: {}", e.getMessage(), e);
        }
    }
}
