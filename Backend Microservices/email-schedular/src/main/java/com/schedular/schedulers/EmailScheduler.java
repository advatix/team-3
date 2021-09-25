package com.schedular.schedulers;

import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.schedular.commons.Logger;
import com.schedular.domain.enums.MessageQueue;
import com.schedular.service.IEmailGatewayConfigDao;
import com.schedular.service.IEmailQueueDao;
import com.schedular.utils.EmailMailService;

@Configuration
@EnableScheduling
public class EmailScheduler {

  private Logger log = Logger.getLogger(getClass());

  @Value("${jobs.enabled:true}")
  private boolean isEnabled;

  @Autowired
  private IEmailQueueDao emailQueueService;

  @Autowired
  private IEmailGatewayConfigDao emailConfigService;

  @Autowired
  private EmailMailService emailService;

  @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
  public void readNewQueue() {
    log.info("Scheduler - {} {}", new Date(), isEnabled);
    if (Boolean.TRUE.equals(isEnabled)) {
      // Set Message Queue Status & Save
      emailQueueService.save(emailQueueService.getNewQueue().parallelStream().map(queue -> {
        queue.setQueueStatus(MessageQueue.COMPLETE.getId());
        return queue;
      }).collect(Collectors.toList())).parallelStream().forEach(emailQueue -> {
        try {
          emailService.send(emailConfigService.findById(emailQueue.getConfigId()), emailQueue);
        } catch (Exception e) {
          log.error(e.getLocalizedMessage(), e);
        }
      });
    }
  }

}
