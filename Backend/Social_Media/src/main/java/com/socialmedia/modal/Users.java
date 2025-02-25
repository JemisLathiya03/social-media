package com.socialmedia.modal;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String gender;

    private String email;

    private String password;

    private String bio;

    @Column(name = "user_image")
    private String userImage;

    @Column(name = "user_name")
    private String userName;

    private Boolean privacy = false;

    @Column(name = "following_users")
    private List<Long> followingUsers = new ArrayList<>();

    @Column(name = "follower_users")
    private List<Long> followerUsers = new ArrayList<>();

    @Column(name = "saved_post")
    private List<Long> savedPost = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "current_theme")
    private Themes currentTheme;

    @Column(name = "available_theme")
    private List<Long> availableThemes;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "is_register")
    private Boolean isRegister = false;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

}
