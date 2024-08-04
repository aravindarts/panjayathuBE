package com.breaksloop.panjayathu.service;

import com.breaksloop.panjayathu.common.LMResponse;
import com.breaksloop.panjayathu.common.MetaConfig;
import com.breaksloop.panjayathu.entity.Status;
import com.breaksloop.panjayathu.entity.User;
import com.breaksloop.panjayathu.repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public LMResponse createNewUser(User user) {
        if(user !=null){
            user.setCreatedTime(LocalDateTime.now());
            user.setStatus(new Status(MetaConfig.Active));
            user.setLastLogged(LocalDateTime.now());
            user.setCountry_code("+91");
            userRepository.save(user);
        }else{
            return LMResponse.failed("User payload is empty", HttpStatus.BAD_REQUEST.value());
        }
        return LMResponse.success("user created", HttpStatus.CREATED.value());
    }
}
