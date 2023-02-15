package com.mert.SpringMono.dto.response;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserControllerFindAllResponseDto {
    String username;
    String avatar;
    Long id;
}
