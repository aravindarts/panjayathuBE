package com.breaksloop.panjayathu.controller;

import com.breaksloop.panjayathu.ChatService;
import com.breaksloop.panjayathu.config.LMResponse;
import com.breaksloop.panjayathu.config.MessagePayload;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatController {


    private final ChatService chatService;


    @GetMapping("/version")
    public LMResponse version(){
        return LMResponse.success("1.0", HttpStatus.OK.value());
    }

    @GetMapping("/chats/{user_id}")
    public LMResponse chats(@PathVariable("user_id") Integer userId){
        return chatService.chats(userId);
    }

    @GetMapping("/messages/{chat_id}")
    public LMResponse chatMessages(@PathVariable("chat_id") Integer chatId){
        return chatService.messages(chatId);
    }

    @PostMapping("/sendMessage/{chat_id}")
    public LMResponse sendMessage(@PathVariable("chat_id") Integer chatId,@RequestBody MessagePayload messagePayload){
        Integer from =6; // get it from filter
        return chatService.sendMessage(chatId,from,messagePayload);
    }
    @PostMapping("/newMessage/{send_to}")
    public LMResponse sendMessageUserId(@PathVariable("send_to") Integer sendTo,
                                        @RequestBody MessagePayload messagePayload){
        Integer from =6; // get it from filter
        return chatService.createAndSendMessage(sendTo,from,messagePayload);
    }



}
