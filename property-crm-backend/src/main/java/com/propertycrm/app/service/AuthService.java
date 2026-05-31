package com.propertycrm.app.service;

import com.propertycrm.app.dto.request.LoginRequest;
import com.propertycrm.app.dto.request.RegisterRequest;
import com.propertycrm.app.dto.response.AuthResponse;

public interface AuthService {

    String register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}	