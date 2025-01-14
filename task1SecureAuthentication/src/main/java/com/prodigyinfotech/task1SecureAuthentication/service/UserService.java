package com.prodigyinfotech.task1SecureAuthentication.service;

import com.prodigyinfotech.task1SecureAuthentication.dto.request.UserLoginRequestDTO;
import com.prodigyinfotech.task1SecureAuthentication.dto.request.UserRegisterRequestDTO;
import com.prodigyinfotech.task1SecureAuthentication.dto.response.MessageDTO;
import com.prodigyinfotech.task1SecureAuthentication.dto.response.UserInfoResponseDTO;
import com.prodigyinfotech.task1SecureAuthentication.dto.response.UserLoginResponseDTO;

import java.util.List;

public interface UserService {
    MessageDTO registerUser(UserRegisterRequestDTO requestDTO);

    MessageDTO registerAdmin(UserRegisterRequestDTO requestDTO);

    UserLoginResponseDTO authenticateUser(UserLoginRequestDTO requestDTO);
    List<UserInfoResponseDTO> getAllUsers();
    UserInfoResponseDTO getUserById(String userId);
}
