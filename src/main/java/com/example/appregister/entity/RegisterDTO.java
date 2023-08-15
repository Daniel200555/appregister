package com.example.appregister.entity;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterDTO {
    private String email;
    private String firstname;
    private String lastname;
    private String password;
}
