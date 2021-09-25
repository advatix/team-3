package com.schedular.service;

import java.util.List;
import com.schedular.domain.SMSQueue;

public interface ISMSQueueDao {

  Long save(SMSQueue entity);

  List<SMSQueue> save(List<SMSQueue> entities);

  List<SMSQueue> getNewQueue();

}
