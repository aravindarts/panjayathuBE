package com.breaksloop.panjayathu.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "chat_id")
    @ManyToOne
    private Chat chat;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "content")
    private String content;

    @Column(name = "send_at")
    private LocalDateTime sendAt;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @Column(name = "seen_at")
    private LocalDateTime seenAt;


}
