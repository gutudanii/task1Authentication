package com.prodigyinfotech.task1SecureAuthentication.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
public class UserLoginResponseDTO {
    private boolean status;
    private String message;
    private String username;
    private String userId;
    private String accessToken;
    private String refreshToken;
    private long expirationTime;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public UserLoginResponseDTO(boolean status, String message, String username, String userId, String accessToken, String refreshToken, long expirationTime) {
        this.status = status;
        this.message = message;
        this.username = username;
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expirationTime = expirationTime;
    }

    @Override
    public String toString() {
        return "UserLoginResponseDTO{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expirationTime=" + expirationTime +
                '}';
    }
}
