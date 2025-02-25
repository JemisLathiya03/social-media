package com.socialmedia.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "themes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Themes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "themes_seq", sequenceName = "themes_seq", allocationSize = 1)
    @Column(name = "theme_id")
    private Long themeId;

    @Column(name = "theme_name")
    private String themeName;

    @Column(name = "primary_color")
    private String primaryColor;

    @Column(name = "secondary_color")
    private String secondaryColor;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "text_color")
    private String textColor;

    @Column(name = "sub_background_color")
    private String subBackgroundColor;

    @Column(name = "sub_text_color")
    private String subTextColor;

    @Column(name = "button_background_color")
    private String buttonBackgroundColor;

    @Column(name = "button_text_color")
    private String buttonTextColor;

    @Column(name = "anchor_text_color")
    private String anchorTextColor;

    @Column(name = "text_field_color")
    private String textFieldColor;

    @Column(name = "created_user")
    private Long createdUser;

    private Boolean modification = false;

    @Column(name = "granted_users")
    private List<Long> grantedUsers = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
}
