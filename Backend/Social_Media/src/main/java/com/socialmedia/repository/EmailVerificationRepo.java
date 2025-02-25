package com.socialmedia.repository;

import com.socialmedia.modal.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmailVerificationRepo extends JpaRepository<EmailVerification, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE EmailVerification e SET e.isExpired = :updateFlag  WHERE e.recipientEmail = :email")
    int updateIsExpiredByEmail(String email, Boolean updateFlag);

    List<EmailVerification> findByRecipientEmailAndIsExpired(String recipientEmail, Boolean isExpired);

}
