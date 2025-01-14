package com.prodigyinfotech.task1SecureAuthentication.service;

import com.prodigyinfotech.task1SecureAuthentication.model.Otp;
import com.prodigyinfotech.task1SecureAuthentication.model.Users;
import com.prodigyinfotech.task1SecureAuthentication.repository.OtpRepository;
import com.prodigyinfotech.task1SecureAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private UserRepository userRepository;

    // Generate a random 4-digit OTP
    public String generateOtp() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

    // Send OTP via email
    @Async
    public void sendOtp(String identifier, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(identifier);
        message.setFrom("gutudanielgeleta@gmail.com");
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp + ". It will expire in 5 minutes.");
        mailSender.send(message);
    }

    // Save OTP to the database
    public void saveOtp(String identifier, String otp) {
        Otp otpEntity = new Otp();
        otpEntity.setIdentifier(identifier);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5)); // Set expiry to 5 minutes
        otpEntity.setVerified(false);

        otpRepository.save(otpEntity);
    }

    // Verify the OTP
    public boolean verifyOtp(String identifier, String otp) {
        Optional<Otp> otpEntity = otpRepository.findByIdentifierAndOtp(identifier, otp);
        Optional<Users> users = userRepository.findByUsername(identifier);
        if (otpEntity.isPresent() && !otpEntity.get().isVerified()) {
            Otp otpData = otpEntity.get();
            if (otpData.getExpiryTime().isAfter(LocalDateTime.now())) {
                otpData.setVerified(true);
                if (users.isPresent()){
                    Users users1 = users.get();
                    users1.setStatus(true);
                    userRepository.save(users1);
                    otpRepository.save(otpData);
                    return true;
                }
                else {
                    return false;
                }

            }
        }
        return false;
    }

    public boolean verifyOtpTest(String identifier, String otp) {
        Optional<Otp> otpEntity = otpRepository.findByIdentifierAndOtp(identifier, otp);
        if (otpEntity.isPresent() && !otpEntity.get().isVerified()) {
            Otp otpData = otpEntity.get();
            if (otpData.getExpiryTime().isAfter(LocalDateTime.now())) {
                otpData.setVerified(true);
                    otpRepository.save(otpData);
                    return true;
                }
            }
        return false;
    }
}
