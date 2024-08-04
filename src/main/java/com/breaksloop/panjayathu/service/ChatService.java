package com.breaksloop.panjayathu.service;

import com.breaksloop.panjayathu.common.LMResponse;
import com.breaksloop.panjayathu.common.MessagePayload;
import com.breaksloop.panjayathu.common.MetaConfig;
import com.breaksloop.panjayathu.entity.*;
import com.breaksloop.panjayathu.repo.ChatRepository;
import com.breaksloop.panjayathu.repo.ChatUserRepository;
import com.breaksloop.panjayathu.repo.MessageRepository;
import com.breaksloop.panjayathu.repo.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ChatService {


    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatUserRepository chatUserRepository;
    private final ObjectMapper objectMapper;

    public LMResponse chats(Integer userId){
        List<Chat> chats =chatRepository.findRecentChatsByUserID(userId);
//        ArrayNode chatsJsonArray = objectMapper.valueToTree(chats);
        return LMResponse.success(chats, HttpStatus.OK.value());
    }

    public LMResponse messages(Integer chatId) {
        List<Message> messages = messageRepository.findLatestMessagesById(chatId);
        return LMResponse.success(messages, HttpStatus.OK.value());
    }

    public LMResponse sendMessage(Integer chatId, Integer from, MessagePayload messagePayload) {
        Chat chat = chatRepository.findById(chatId).orElseThrow();
        Message message = Message.builder().content(messagePayload.getContent())
                .sendAt(messagePayload.getSendAt()!=null?messagePayload.getSendAt(): LocalDateTime.now())
                .user(new User(from))
                .content(messagePayload.getContent())
                .chat(chat)
                .build();
        messageRepository.save(message);
        chat.setLastMessage(message);
        chatRepository.save(chat);
        return LMResponse.success("Message Sent", HttpStatus.CREATED.value());
    }

    public LMResponse createAndSendMessage(Integer sendTo, Integer from, MessagePayload messagePayload) {
        User toUser = userRepository.findById(sendTo).orElseThrow();
        User fromUser = userRepository.findById(from).orElseThrow();
        Chat chat =Chat.builder().name(toUser.getName()) //Primary chat name will be auto gen
                .chatType(MetaConfig.PRIMARY_CHAT)
                .lastReceivedAt(messagePayload.getSendAt())
                .build();
        createNewChat(chat,fromUser,toUser);
        Message message = Message.builder().content(messagePayload.getContent())
                .sendAt(messagePayload.getSendAt()!=null?messagePayload.getSendAt(): LocalDateTime.now())
                .user(new User(from))
                .content(messagePayload.getContent())
                .chat(new Chat(chat.getId()))
                .build();
        messageRepository.save(message);
        chat.setLastMessage(message);
        chatRepository.save(chat);
        return LMResponse.success("Message Sent", HttpStatus.CREATED.value());
    }

    private void createNewChat(Chat chat, User fromUser, User toUser) {
        chatRepository.save(chat);
        ChatUser chatUser = ChatUser.builder()
                .from(fromUser)
                .chat(chat)
                .status(new Status(MetaConfig.Active))
                .to(toUser).build();
        ChatUser chatUser2 = ChatUser.builder()
                .from(toUser)
                .chat(chat)
                .status(new Status(MetaConfig.Active))
                .to(fromUser).build();
        List<ChatUser> chatUsers = Arrays.asList(chatUser,chatUser2);
        chatUserRepository.saveAll(chatUsers);
    }

}
