package com.prodigyinfotech.task1SecureAuthentication.service.impl;

import com.prodigyinfotech.task1SecureAuthentication.dto.request.UserLoginRequestDTO;
import com.prodigyinfotech.task1SecureAuthentication.dto.request.UserRegisterRequestDTO;
import com.prodigyinfotech.task1SecureAuthentication.dto.response.MessageDTO;
import com.prodigyinfotech.task1SecureAuthentication.dto.response.UserInfoResponseDTO;
import com.prodigyinfotech.task1SecureAuthentication.dto.response.UserLoginResponseDTO;
import com.prodigyinfotech.task1SecureAuthentication.model.User_Role;
import com.prodigyinfotech.task1SecureAuthentication.model.Users;
import com.prodigyinfotech.task1SecureAuthentication.repository.UserRepository;
import com.prodigyinfotech.task1SecureAuthentication.service.OtpService;
import com.prodigyinfotech.task1SecureAuthentication.service.UserService;
import com.prodigyinfotech.task1SecureAuthentication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public MessageDTO registerUser(UserRegisterRequestDTO requestDTO) {
        try {
        Users user = new Users();
            user.setUsername(requestDTO.getUsername());
            user.setEmail(requestDTO.getEmail());
            user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
            user.setFirstName(requestDTO.getFirstName());
            user.setLastName(requestDTO.getLastName());
            user.setRole(User_Role.ROLE_USER);
            user.setStatus(false);
            userRepository.save(user);
            String otp = otpService.generateOtp();
            otpService.sendOtp(user.getEmail(), otp);
            otpService.saveOtp(user.getEmail() ,otp);
            return new MessageDTO(true, "User is successfully registered and OTP Sent");
        }
        catch (Exception e){
            return new MessageDTO(false, "Error :- " + e);
        }

    }

    @Override
    public MessageDTO registerAdmin(UserRegisterRequestDTO requestDTO) {
        try {
            Users user = new Users();
            user.setUsername(requestDTO.getUsername());
            user.setEmail(requestDTO.getEmail());
            user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
            user.setFirstName(requestDTO.getFirstName());
            user.setLastName(requestDTO.getLastName());
            user.setRole(User_Role.ROLE_ADMIN);
            user.setStatus(false);
            userRepository.save(user);
            String otp = otpService.generateOtp();
            otpService.sendOtp(user.getEmail(), otp);
            otpService.saveOtp(user.getEmail() ,otp);
            return new MessageDTO(true, "Admin is successfully registered && OTP Sent");
        }
        catch (Exception e) {
            return new MessageDTO(false, "Error :- " + e);
        }
    }

    @Override
    public UserLoginResponseDTO authenticateUser(UserLoginRequestDTO requestDTO) {
       try {
           Users user = userRepository.findByUsername(requestDTO.getUsername())
                   .orElseThrow(() -> new RuntimeException("User not found"));
           if (!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
               return new UserLoginResponseDTO(
                       false, // status
                       "Invalid Credentials", // message
                       null, // username (assuming it's not available in case of an error)
                       null, // userId (assuming it's not available in case of an error)
                       null, // accessToken (assuming it's not available in case of an error)
                       null, // refreshToken (assuming it's not available in case of an error)
                       0 // expirationTime (assuming it's not relevant in case of an error)
               );
           }
           if (!user.isStatus()){
               return new UserLoginResponseDTO(
                       false, // status
                       "User is not verified", // message
                       null, // username (assuming it's not available in case of an error)
                       null, // userId (assuming it's not available in case of an error)
                       null, // accessToken (assuming it's not available in case of an error)
                       null, // refreshToken (assuming it's not available in case of an error)
                       0 // expirationTime (assuming it's not relevant in case of an error)
               );
           }

           String accessToken = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
           String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

           return new UserLoginResponseDTO(
                   true, // status
                   "Logged in successfully", // message
                   user.getUsername(), // username
                   user.getId(), // userId
                   accessToken, // accessToken
                   refreshToken, // refreshToken
                   System.currentTimeMillis() + (1000 * 60 * 60 * 10) // expirationTime
           );
       }
       catch (Exception e){
           return new UserLoginResponseDTO(
                   false, // status
                   "Error :-" + e.getMessage(), // message
                   null, // username (assuming it's not available in case of an error)
                   null, // userId (assuming it's not available in case of an error)
                   null, // accessToken (assuming it's not available in case of an error)
                   null, // refreshToken (assuming it's not available in case of an error)
                   0 // expirationTime (assuming it's not relevant in case of an error)
           );
       }
    }

    @Override
    public List<UserInfoResponseDTO> getAllUsers() {
        // Fetch all users from the repository
        List<Users> users = userRepository.findAll();

        // Map each user entity to a UserInfoResponseDTO
        return users.stream()
                .map(user -> new UserInfoResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.isStatus(),
                        user.getRole().name() // Changed to single role
                ))
                .collect(Collectors.toList());
    }

    @Override
    public UserInfoResponseDTO getUserById(String userId) {
        // Fetch user by ID
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Map the user entity to a UserInfoResponseDTO
        return new UserInfoResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.isStatus(),
                user.getRole().name()
        );
    }
}
