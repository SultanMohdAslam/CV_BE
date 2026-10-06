package com.cv.service;

import com.cv.entity.AdminSecurity;
import com.cv.repository.AdminSecurityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminSecurityService {

    private static final Logger log = LoggerFactory.getLogger(AdminSecurityService.class);
    private static final String DEFAULT_PASSCODE = "12345";

    private final AdminSecurityRepository adminSecurityRepository;

    public AdminSecurityService(AdminSecurityRepository adminSecurityRepository) {
        this.adminSecurityRepository = adminSecurityRepository;
    }

    @Transactional
    public void initializeDefaultPasscode() {
        if (adminSecurityRepository.count() == 0) {
            AdminSecurity security = new AdminSecurity(DEFAULT_PASSCODE);
            adminSecurityRepository.save(security);
            log.info("Initialized default admin security passcode (12345).");
        }
    }

    @Transactional(readOnly = true)
    public boolean verifyPasscode(String inputPasscode) {
        if (inputPasscode == null) {
            return false;
        }

        AdminSecurity security = adminSecurityRepository.findFirstByOrderByIdAsc()
                .orElse(null);

        if (security == null) {
            return DEFAULT_PASSCODE.equals(inputPasscode.trim());
        }

        return security.getPasscode().equals(inputPasscode.trim());
    }

    @Transactional
    public boolean updatePasscode(String currentPasscode, String newPasscode) {
        if (newPasscode == null || newPasscode.trim().isEmpty()) {
            throw new IllegalArgumentException("New passcode cannot be empty.");
        }

        AdminSecurity security = adminSecurityRepository.findFirstByOrderByIdAsc()
                .orElseGet(() -> new AdminSecurity(DEFAULT_PASSCODE));

        if (!security.getPasscode().equals(currentPasscode != null ? currentPasscode.trim() : "")) {
            return false;
        }

        security.setPasscode(newPasscode.trim());
        adminSecurityRepository.save(security);
        log.info("Admin passcode successfully updated.");
        return true;
    }
}
