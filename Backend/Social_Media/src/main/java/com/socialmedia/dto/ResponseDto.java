package com.socialmedia.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {

    private Integer code;

    private String message;

    private List<T> data;

    private boolean status;

}
