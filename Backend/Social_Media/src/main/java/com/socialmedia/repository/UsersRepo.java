package com.socialmedia.repository;

import com.socialmedia.modal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsersRepo extends JpaRepository<Users, Long> {
    List<Users> findUserByUserName(String userName);

    List<Users> findUserByEmail(String email);

    List<Users> findUserByEmailAndIsRegister(String email, Boolean isRegister);

//    @Modifying
//    @Transactional
//    @Query("UPDATE Users u SET u.isRegister = true  WHERE u.email = :email")
//    int updateIsRegisterByEmail(String email);
}
