package com.tyche.dtos;

import com.tyche.domain.user.UserType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter

public class RespUserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal balance;
    private UserType userType;
    private Boolean status;

    public RespUserDTO() {

    }

    public RespUserDTO(Long id, String firstName, String lastName, String email, BigDecimal balance, UserType userType, Boolean status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.balance = balance;
        this.userType = userType;
        this.status = status;
    }

    // Getters and Setters
}
