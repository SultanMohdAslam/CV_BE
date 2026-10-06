package com.cv.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdatePasscodeRequest {

    @NotBlank(message = "Current passcode is required")
    private String currentPasscode;

    @NotBlank(message = "New passcode is required")
    private String newPasscode;

    public UpdatePasscodeRequest() {
    }

    public UpdatePasscodeRequest(String currentPasscode, String newPasscode) {
        this.currentPasscode = currentPasscode;
        this.newPasscode = newPasscode;
    }

    public String getCurrentPasscode() {
        return currentPasscode;
    }

    public void setCurrentPasscode(String currentPasscode) {
        this.currentPasscode = currentPasscode;
    }

    public String getNewPasscode() {
        return newPasscode;
    }

    public void setNewPasscode(String newPasscode) {
        this.newPasscode = newPasscode;
    }
}
