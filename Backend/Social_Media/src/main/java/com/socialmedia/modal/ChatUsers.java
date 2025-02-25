package com.socialmedia.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_users_seq")
    @SequenceGenerator(name = "chat_users_seq", sequenceName = "chat_users_seq", allocationSize = 1)
    @Column(name = "chat_users_id")
    private Long chatUserId;

    @ManyToOne
    @JoinColumn(name = "chat")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "users")
    private Users users;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

}
