package com.breaksloop.panjayathu.repo;

import com.breaksloop.panjayathu.entity.Status;
import com.breaksloop.panjayathu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status,Integer> {

}
