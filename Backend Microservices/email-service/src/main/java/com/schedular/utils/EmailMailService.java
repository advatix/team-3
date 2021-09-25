package com.schedular.utils;

import java.awt.TrayIcon.MessageType;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.schedular.commons.StringUtils;
import com.schedular.domain.EmailQueue;
import com.schedular.domain.SMTPConfig;
import com.schedular.domain.enums.MessageQueue;
import com.schedular.service.IEmailQueueDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailMailService {

  @Autowired
  private IEmailQueueDao emailQueueService;

  public EmailMailService() {
    super();
  }

  @Async("processExecutor")
  public void send(SMTPConfig config, EmailQueue queue) {
    log.info(config.toString());
    log.info(queue.toString());
    
    // Sender's email ID needs to be mentioned

    final String username = config.getUsername();
    final String password = config.getPassword();

    // Assuming you are sending email through relay.jangosmtp.net
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", config.getHost());
    props.put("mail.smtp.port", String.valueOf(config.getPort()));

    // Get the Session object.
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });

    try {
      // Create a default MimeMessage object.
      Message message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(config.getMailFrom(), config.getMailFromName()));

      // Set To: header field of the header.
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(queue.getMailTo()));

      // Set CC: header field of the header.
      if (Boolean.TRUE.equals(StringUtils.isNotBlank(queue.getMailTocc())))
        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(queue.getMailTocc()));

      // Set BCC: header field of the header.
      if (Boolean.TRUE.equals(StringUtils.isNotBlank(queue.getMailTobcc())))
        message.setRecipients(Message.RecipientType.BCC,
            InternetAddress.parse(queue.getMailTobcc()));

      // Set Subject: header field
      message.setSubject(queue.getSubject());

      message.setContent(queue.getMessage(), queue.getContentType());

      // Send message
      Transport.send(message);

      queue.setMessageSent(Boolean.TRUE);
      log.info("Mail sent successfully");
    } catch (Exception e) {
      log.info("Email Queue Batch Id : {}", queue.getBatchId().toString());
      log.error(e.getLocalizedMessage(), e);
      queue.setQueueStatus(MessageQueue.FAILED.getId());
    }
    emailQueueService.save(queue);
  }

}
