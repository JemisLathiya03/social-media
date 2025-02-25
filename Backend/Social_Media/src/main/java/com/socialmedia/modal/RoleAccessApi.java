package com.socialmedia.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role_access_api")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAccessApi {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_access_api_seq")
    @SequenceGenerator(name = "role_access_api_seq", sequenceName = "role_access_api_seq", allocationSize = 1)
    @Column(name = "role_access_api_id")
    private Long roleAccessApiId;

    @OneToOne
    private Role role;

    @OneToOne
    private AccessApi accessApi;
}
