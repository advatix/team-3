package com.schedular.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.schedular.domain.SMSMessageLogs;

@Repository
@Transactional
public interface SMSMessageLogsRepository extends JpaRepository<SMSMessageLogs, Long> {

  Optional<SMSMessageLogs> findTopByMessageIdOrderByCreatedOnDesc(String messageId);

}
