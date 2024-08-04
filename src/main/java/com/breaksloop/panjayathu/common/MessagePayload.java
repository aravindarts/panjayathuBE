package com.breaksloop.panjayathu.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessagePayload {

    private MessageType messageType;
    private String content;
    private LocalDateTime sendAt;

}
