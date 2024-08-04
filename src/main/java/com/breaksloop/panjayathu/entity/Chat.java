package com.breaksloop.panjayathu.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chat")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @JoinColumn(name = "last_message_id")
    @ManyToOne
    private Message lastMessage;

    @OneToMany(mappedBy = "chat")
    private List<ChatUser> chatUsers;
    public Chat(Integer id) {
        this.id = id;
    }
}
