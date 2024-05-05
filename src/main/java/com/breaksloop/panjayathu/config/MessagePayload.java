package com.breaksloop.panjayathu.config;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessagePayload {

    private MessageType messageType;
    private String content;
    private LocalDateTime sendAt;

}
