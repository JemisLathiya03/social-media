package com.socialmedia.mapper;

import com.socialmedia.dto.UsersDto;
import com.socialmedia.modal.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    public Users usersDtoToUser(UsersDto usersDto);

}
