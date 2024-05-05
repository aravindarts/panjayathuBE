package com.breaksloop.panjayathu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat",schema = "master")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "chat_type")
    private String chatType;

    @Column(name = "last_received_at")
    private LocalDateTime lastReceivedAt;

    public Chat(Integer id) {
        this.id = id;
    }
}
