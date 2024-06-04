package com.tyche.dtos;

public record UserCreateDTO(String firstName, String lastName, String document, String email, String password) {
}
