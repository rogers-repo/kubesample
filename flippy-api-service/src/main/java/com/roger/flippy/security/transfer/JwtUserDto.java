package com.roger.flippy.security.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserDto {

    private Long id;

    private String username;

    private String address;

    private String role;


}