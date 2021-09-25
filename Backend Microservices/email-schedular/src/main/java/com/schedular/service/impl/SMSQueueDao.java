package com.schedular.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.schedular.commons.Logger;
import com.schedular.domain.SMSQueue;
import com.schedular.domain.enums.MessageQueue;
import com.schedular.repository.SMSQueueRepository;
import com.schedular.service.ISMSQueueDao;

@Service
@Transactional
public class SMSQueueDao implements ISMSQueueDao {

  private Logger log = Logger.getLogger(getClass());

  @Autowired
  private SMSQueueRepository repository;

  @Override
  public Long save(SMSQueue entity) {
    return repository.save(entity).getId();
  }

  @Override
  public List<SMSQueue> save(List<SMSQueue> entities) {
    return repository.saveAll(entities);
  }

  @Override
  public List<SMSQueue> getNewQueue() {
    log.info("SELECT * FROM SMS_QUEUE WHERE QueueStatus = {} AND MessageSent = 0",
        MessageQueue.INQUEUE.getId());
    return repository.findAllByQueueStatusAndMessageSentFalse(MessageQueue.INQUEUE.getId());
  }

}
