package com.socialmedia.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "reel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reel_seq")
    @SequenceGenerator(name = "reel_seq", sequenceName = "reel_seq", allocationSize = 1)
    @Column(name = "reel_id")
    private Long reelId;

    @Column(name = "reel_caption")
    private String reelCaption;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users createdBy;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "liked_users")
    private List<Long> likedUsers;

    @Column(name = "reel_content")
    private String reelContent;


}
