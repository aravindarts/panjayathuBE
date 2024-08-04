package com.breaksloop.panjayathu.controller;

import com.breaksloop.panjayathu.service.ChatService;
import com.breaksloop.panjayathu.common.LMResponse;
import com.breaksloop.panjayathu.common.MessagePayload;
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

    @GetMapping("/{user_id}")
    public LMResponse chats(@PathVariable("user_id") Integer userId){
        return chatService.chats(userId);
    }

    @GetMapping("/messages/{chat_id}")
    public LMResponse chatMessages(@PathVariable("chat_id") Integer chatId){
        return chatService.messages(chatId);
    }


    @PostMapping("/send/{user_id}/{chat_id}")
    public LMResponse sendMessage(@PathVariable("user_id") Integer userId,//It will remove later
                                  @PathVariable("chat_id") Integer chatId,
                                  @RequestBody MessagePayload messagePayload){
        Integer from =1; // get it from filter later
        return chatService.sendMessage(chatId,userId,messagePayload);
    }
    @PostMapping("/new/{user_id}/{send_to}")
    public LMResponse sendMessageUserId(@PathVariable("user_id") Integer userId,//It will remove later
                                        @PathVariable("send_to") Integer sendTo,
                                        @RequestBody MessagePayload messagePayload){
        Integer from =1; // get it from filter later
        return chatService.createAndSendMessage(sendTo,userId,messagePayload);
    }



}
