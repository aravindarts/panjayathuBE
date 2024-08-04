package com.breaksloop.panjayathu.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "country_code")
    private String country_code;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @JoinColumn(name = "status")
    @ManyToOne
    private Status status;

    @Column(name = "last_logged_in")
    private LocalDateTime lastLogged;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "deactivated_time")
    private LocalDateTime deactivatedTime;

    @OneToOne(mappedBy = "user")
    @JsonManagedReference

    private UserProfile userProfile;

    public User(Integer id) {
        this.id = id;
    }
}
