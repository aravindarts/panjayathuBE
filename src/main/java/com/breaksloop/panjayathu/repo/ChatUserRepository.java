package com.breaksloop.panjayathu.repo;

import com.breaksloop.panjayathu.entity.ChatUser;
import com.breaksloop.panjayathu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser,Integer> {

}
