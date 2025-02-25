package com.socialmedia.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "access_api")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessApi {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "access_api_seq")
    @SequenceGenerator(name = "access_api_seq", sequenceName = "access_api_seq", allocationSize = 1)
    @Column(name = "access_api_id")
    private Long accessApiId;

    @Column(name = "access_api_name")
    private String accessApiName;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
}
