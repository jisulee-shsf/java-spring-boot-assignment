package com.sparta.domain.admin.dto;

import com.sparta.domain.admin.entity.Admin;
import com.sparta.domain.admin.entity.AdminRoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public class SignupResponseDto {
    private Long id;
    private String email;
    private String password;
    private String department;

    @Enumerated(value = EnumType.STRING)
    private AdminRoleEnum role;

    public SignupResponseDto(Admin saveAdmin) {
        this.id = saveAdmin.getId();
        this.email = saveAdmin.getEmail();
        this.password = saveAdmin.getPassword();
        this.department = saveAdmin.getDepartment();
        this.role = saveAdmin.getRole();
    }
}