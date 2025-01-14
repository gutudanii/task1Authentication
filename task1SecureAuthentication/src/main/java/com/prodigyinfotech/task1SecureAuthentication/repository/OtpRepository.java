package com.prodigyinfotech.task1SecureAuthentication.repository;

import com.prodigyinfotech.task1SecureAuthentication.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByIdentifierAndOtp(String identifier, String otp);
}