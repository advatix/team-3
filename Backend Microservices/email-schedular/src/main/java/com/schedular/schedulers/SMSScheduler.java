package com.schedular.schedulers;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.schedular.commons.Logger;
import com.schedular.domain.SMSQueue;
import com.schedular.domain.enums.MessageQueue;
import com.schedular.service.ISMSQueueDao;
import com.schedular.utils.Constant;
import com.schedular.utils.SMSService;

@Configuration
@EnableScheduling
public class SMSScheduler {

  private Logger log = Logger.getLogger(getClass());

  @Value("${jobs.enabled:true}")
  private boolean isEnabled;

  @Autowired
  private ISMSQueueDao smsQueueService;

  @Autowired
  private SMSService smsService;

  @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
  public void readNewQueue() {
    log.info("Scheduler - {} {}", new Date(), isEnabled);
    if (Boolean.TRUE.equals(isEnabled)) {
      // Set Message Queue Status & Save
      smsQueueService.save(smsQueueService.getNewQueue().parallelStream().map(queue -> {
        queue.setAttempts(queue.getAttempts() + 1);
        queue.setQueueStatus(MessageQueue.COMPLETE.getId());
        return queue;
      }).collect(Collectors.toList()))
          // Split Contact Numbers with comma seperator for pooling
          .parallelStream().map(this::splitMessageQueue).flatMap(Collection::stream)
          .forEach(smsService::sendSmsPool);
    }
  }

  private List<SMSQueue> splitMessageQueue(SMSQueue queue) {
    return Arrays.asList(queue.getContactNumber().split(Constant.COMMA_SEPERATOR)).parallelStream()
        .map(contact -> queue.copyProperties().withContactNumber(contact))
        .collect(Collectors.toList());
  }

}
