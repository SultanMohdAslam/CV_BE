package com.cv.dto;

import jakarta.validation.constraints.NotBlank;

public class VerifyPasscodeRequest {

    @NotBlank(message = "Passcode is required")
    private String passcode;

    public VerifyPasscodeRequest() {
    }

    public VerifyPasscodeRequest(String passcode) {
        this.passcode = passcode;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
}
