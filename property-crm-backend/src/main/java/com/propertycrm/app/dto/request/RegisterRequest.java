package com.propertycrm.app.dto.request;

import com.propertycrm.app.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String username;
    private String email;
    private String password;
    private Role role;
}