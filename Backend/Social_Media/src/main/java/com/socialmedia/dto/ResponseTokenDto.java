package com.socialmedia.dto;

import com.socialmedia.modal.Themes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTokenDto {

    private String token;
    private String refreshToken;
    private Themes themes;
    private String email;

}
