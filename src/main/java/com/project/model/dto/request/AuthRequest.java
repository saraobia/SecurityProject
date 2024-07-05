package com.project.model.dto.request;

import lombok.Data;
import lombok.Getter;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
