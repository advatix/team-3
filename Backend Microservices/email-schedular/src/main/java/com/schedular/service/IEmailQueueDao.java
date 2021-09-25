package com.schedular.service;

import java.util.List;
import com.schedular.domain.EmailQueue;

public interface IEmailQueueDao {

  Long save(EmailQueue entity);

  List<EmailQueue> save(List<EmailQueue> entities);

  List<EmailQueue> getNewQueue();

}
