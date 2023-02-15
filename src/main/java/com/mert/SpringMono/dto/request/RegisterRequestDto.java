package com.mert.SpringMono.dto.request;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterRequestDto {
    String username;
    String password;
    String email;
}
