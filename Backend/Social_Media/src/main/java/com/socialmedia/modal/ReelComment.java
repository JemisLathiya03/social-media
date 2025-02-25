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
@Table(name = "reel_comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReelComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reel_comment_seq")
    @SequenceGenerator(name = "reel_comment_seq", sequenceName = "reel_comment_seq", allocationSize = 1)
    @Column(name = "reel_comment_id")
    private Long reelCommentId;

    private String content;

    @Column(name = "liked_users")
    private List<Long> likedUsers;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users createdBy;

    @ManyToOne
    private Reel reel;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
}
