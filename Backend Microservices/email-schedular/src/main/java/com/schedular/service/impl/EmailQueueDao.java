package com.schedular.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.schedular.commons.Logger;
import com.schedular.domain.EmailQueue;
import com.schedular.domain.enums.MessageQueue;
import com.schedular.repository.EmailQueueRepository;
import com.schedular.service.IEmailQueueDao;

@Service
@Transactional
public class EmailQueueDao implements IEmailQueueDao {

  private Logger log = Logger.getLogger(getClass());

  @Autowired
  private EmailQueueRepository repository;

  @Override
  public Long save(EmailQueue entity) {
    return repository.save(entity).getId();
  }

  @Override
  public List<EmailQueue> save(List<EmailQueue> entities) {
    return repository.saveAll(entities);
  }

  @Override
  public List<EmailQueue> getNewQueue() {
    log.info("SELECT * FROM EMAIL_QUEUE WHERE QueueStatus = {} AND MessageSent = 0",
        MessageQueue.INQUEUE.getId());
    return repository.findAllByQueueStatusAndMessageSentFalse(MessageQueue.INQUEUE.getId());
  }

}
