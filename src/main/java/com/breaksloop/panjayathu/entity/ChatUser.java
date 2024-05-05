package com.breaksloop.panjayathu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "chat_user",schema = "master")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "chat_id")
    @ManyToOne
    private Chat chat;

    @JoinColumn(name = "from_id")
    @ManyToOne
    private User from;

    @JoinColumn(name = "to_id")
    @ManyToOne
    private User to;

    @JoinColumn(name = "status")
    @ManyToOne
    private Status status;

    public ChatUser(Integer id) {
        this.id = id;
    }
}
