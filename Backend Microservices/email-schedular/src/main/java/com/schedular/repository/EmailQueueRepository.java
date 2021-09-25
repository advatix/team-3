package com.schedular.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.schedular.domain.EmailQueue;

@Repository
@Transactional
public interface EmailQueueRepository extends JpaRepository<EmailQueue, Long> {

  List<EmailQueue> findAllByQueueStatusAndMessageSentFalse(Integer id);

}
