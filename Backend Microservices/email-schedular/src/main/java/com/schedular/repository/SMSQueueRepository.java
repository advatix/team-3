package com.schedular.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.schedular.domain.SMSQueue;

@Repository
@Transactional
public interface SMSQueueRepository extends JpaRepository<SMSQueue, Long> {

  List<SMSQueue> findAllByQueueStatusAndMessageSentFalse(Integer queueStatus);

}
