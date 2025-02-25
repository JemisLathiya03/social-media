package com.socialmedia.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_content")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostContent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_content_seq")
    @SequenceGenerator(name = "post_content_seq", sequenceName = "post_content_seq", allocationSize = 1)
    @Column(name = "post_content_id")
    private Long postContentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "post_content_path")
    private String postContentPath;
}
