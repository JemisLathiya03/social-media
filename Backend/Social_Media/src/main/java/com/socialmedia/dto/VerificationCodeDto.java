package com.socialmedia.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationCodeDto {

    @NotBlank
    @Size(min = 2,max = 40)
    @Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")
    private String email;


    @NotNull
    @Digits(integer = 6, fraction = 0)
    private Integer verificationCode;

}
