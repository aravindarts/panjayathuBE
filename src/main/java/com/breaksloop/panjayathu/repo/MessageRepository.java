package com.breaksloop.panjayathu.repo;

import com.breaksloop.panjayathu.entity.Message;
import com.breaksloop.panjayathu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

    @Query(value = "select * from master.message where chat_id =?1 order by send_at desc",nativeQuery = true)
    List<Message> findLatestMessagesById(Integer chatId);
}
