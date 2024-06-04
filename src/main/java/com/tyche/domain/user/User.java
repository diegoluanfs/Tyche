package com.tyche.domain.user;

import com.tyche.dtos.UserCreateDTO;
import com.tyche.dtos.UserUpdateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column (unique=true)
    private String document;
    @Column(unique=true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private Boolean status;

    public User(UserCreateDTO data){
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.document = data.document();
        this.password = data.password();
        this.email = data.email();
        this.balance = BigDecimal.valueOf(0);
        this.userType = UserType.COMMON;
        this.status = true;
    }

    public User(UserUpdateDTO data){
        this.id = data.id();
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.password = data.password();
    }
}
