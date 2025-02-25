package com.socialmedia.service.abstraction;

import com.socialmedia.dto.ResponseDto;
import com.socialmedia.dto.ResponseTokenDto;
import com.socialmedia.dto.UsersDto;
import com.socialmedia.dto.VerificationCodeDto;
import com.socialmedia.modal.Users;

public interface AuthUserDetailService {

    public ResponseDto<Object> registerUserService(UsersDto usersDto);

    public ResponseDto<Object> verificationCodeService(VerificationCodeDto verificationCodeDto);

    public ResponseDto<Object> getHomeDataService();


}
