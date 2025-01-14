package com.prodigyinfotech.task1SecureAuthentication.controller;

import com.prodigyinfotech.task1SecureAuthentication.dto.request.UserLoginRequestDTO;
import com.prodigyinfotech.task1SecureAuthentication.dto.request.UserRegisterRequestDTO;
import com.prodigyinfotech.task1SecureAuthentication.dto.response.MessageDTO;
import com.prodigyinfotech.task1SecureAuthentication.dto.response.UserInfoResponseDTO;
import com.prodigyinfotech.task1SecureAuthentication.dto.response.UserLoginResponseDTO;
import com.prodigyinfotech.task1SecureAuthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint for user registration.
     * @param requestDTO The registration details from the client.
     * @return UserRegisterResponseDTO containing the registered user's details.
     */
    @PostMapping("/register")
    @Async
    public MessageDTO registerUser(@RequestBody UserRegisterRequestDTO requestDTO) {
        return userService.registerUser(requestDTO);
    }

    /**
     * Endpoint for user registration.
     * @param requestDTO The registration details from the client.
     * @return UserRegisterResponseDTO containing the registered user's details.
     */
    @PostMapping("/register/admin")
    public MessageDTO registerAdmin(@RequestBody UserRegisterRequestDTO requestDTO) {
        return userService.registerAdmin(requestDTO);
    }

    /**
     * Endpoint for user login.
     * @param requestDTO The login credentials from the client.
     * @return UserLoginResponseDTO containing the access token, refresh token, and expiration time.
     */
    @PostMapping("/login")
    public UserLoginResponseDTO authenticateUser(@RequestBody UserLoginRequestDTO requestDTO) {
        return userService.authenticateUser(requestDTO);
    }


    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfoResponseDTO getUserInfo(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

        @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserInfoResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

}
