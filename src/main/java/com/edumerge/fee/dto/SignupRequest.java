package com.edumerge.fee.dto;

import com.edumerge.fee.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    private String name;
    private String email;
    private String password;
    private Role role;
}