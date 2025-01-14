package com.prodigyinfotech.task1SecureAuthentication.controller;

import com.prodigyinfotech.task1SecureAuthentication.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/generate/test")
    public ResponseEntity<String> generateOtp(@RequestParam String identifier) {
        String otp = otpService.generateOtp();
        otpService.saveOtp(identifier, otp);
        otpService.sendOtp(identifier, otp);
        return ResponseEntity.ok("OTP has been sent to " + identifier);
    }

    @PostMapping("/verify/test")
    public ResponseEntity<String> verifyOtpTest(@RequestParam String identifier, @RequestParam String otp) {
        boolean isVerified = otpService.verifyOtpTest(identifier, otp);
        if (isVerified) {
            return ResponseEntity.ok("OTP verified successfully.");
        }
        return ResponseEntity.badRequest().body("Invalid or expired OTP.");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String identifier, @RequestParam String otp) {
        boolean isVerified = otpService.verifyOtp(identifier, otp);
        if (isVerified) {
            return ResponseEntity.ok("OTP verified successfully.");
        }
        return ResponseEntity.badRequest().body("Invalid or expired OTP.");
    }
}
