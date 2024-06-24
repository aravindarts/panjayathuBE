package com.breaksloop.panjayathu.repo;

import com.breaksloop.panjayathu.entity.Chat;
import com.breaksloop.panjayathu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Integer> {


    @Query(value = "select c.* from master.chat as c " +
            "join master.chat_user cu on cu.chat_id=c.id " +
            "where cu.from_id =?1 ORDER BY c.last_received_at desc",nativeQuery = true)
    List<Chat> findRecentChatsByUserID(Integer userId);
}
