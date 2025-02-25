package com.socialmedia.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    @SequenceGenerator(name = "post_seq", sequenceName = "post_seq", allocationSize = 1)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "created_user")
    private Users createdUser;

    @OneToMany(mappedBy = "post")
    private List<PostContent> postContents;

    @Column(name = "post_caption")
    private String postCaption;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "post_location")
    private String postLocation;

    @Column(name = "liked_users")
    private List<Long> likedUsers;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

}
