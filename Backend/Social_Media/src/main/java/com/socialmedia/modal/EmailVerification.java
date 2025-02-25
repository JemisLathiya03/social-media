package com.socialmedia.modal;

import com.socialmedia.enumeration.VerificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_verification_seq")
    @SequenceGenerator(name = "email_verification_seq", sequenceName = "email_verification_seq", allocationSize = 1)
    @Column(name = "email_verification_id")
    private Long emailVerificationId;

    @Column(name = "recipient_email")
    private String recipientEmail;

    @Column(name = "verification_code")
    private Integer verificationCode;

    @Enumerated(EnumType.ORDINAL)
    private VerificationType verificationType;

    @Column(name = "is_expired")
    private Boolean isExpired = false;

    private LocalDateTime expirationTime;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;
}
