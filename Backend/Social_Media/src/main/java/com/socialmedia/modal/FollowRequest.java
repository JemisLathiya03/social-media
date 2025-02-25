package com.socialmedia.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "follow_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "follow_request_seq")
    @SequenceGenerator(name = "follow_request_seq", sequenceName = "follow_request_seq", allocationSize = 1)
    @Column(name = "follow_request_id")
    private Long followRequestId;

    @OneToOne
    @JoinColumn(name = "requested_user")
    private Users requestedUser;

    @OneToOne
    @JoinColumn(name = "target_user")
    private Users targetUser;

    @Column(name = "request_status")
    private Boolean requestStatus;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;


}
