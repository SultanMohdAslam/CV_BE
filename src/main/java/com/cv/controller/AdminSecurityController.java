package com.cv.controller;

import com.cv.dto.UpdatePasscodeRequest;
import com.cv.dto.VerifyPasscodeRequest;
import com.cv.service.AdminSecurityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/security")
@CrossOrigin(origins = "*")
public class AdminSecurityController {

    private final AdminSecurityService adminSecurityService;

    public AdminSecurityController(AdminSecurityService adminSecurityService) {
        this.adminSecurityService = adminSecurityService;
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyPasscode(@Valid @RequestBody VerifyPasscodeRequest request) {
        boolean valid = adminSecurityService.verifyPasscode(request.getPasscode());
        if (valid) {
            return ResponseEntity.ok(Map.of(
                    "valid", true,
                    "message", "Admin authentication successful"
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "valid", false,
                    "message", "Incorrect admin passcode. Access denied."
            ));
        }
    }

    @PutMapping("/passcode")
    public ResponseEntity<Map<String, Object>> updatePasscode(@Valid @RequestBody UpdatePasscodeRequest request) {
        boolean updated = adminSecurityService.updatePasscode(
                request.getCurrentPasscode(),
                request.getNewPasscode()
        );

        if (updated) {
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Admin passcode updated successfully"
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "success", false,
                    "message", "Current passcode is incorrect. Passcode was not changed."
            ));
        }
    }
}
