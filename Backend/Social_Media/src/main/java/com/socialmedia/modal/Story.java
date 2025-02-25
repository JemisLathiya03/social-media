package com.socialmedia.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "story")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "story_seq")
    @SequenceGenerator(name = "story_seq", sequenceName = "story_seq", allocationSize = 1)
    @Column(name = "story_id")
    private Long storyId;

    @Column(name = "story_content")
    private String storyContent;

    @ManyToOne
    @JoinColumn(name = "created_user")
    private Users createdUser;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "is_time_complete")
    private Boolean isTimeComplete;

    @Column(name = "view_users")
    private List<Long> viewUsers;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

}
