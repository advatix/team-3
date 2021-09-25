package com.schedular.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.schedular.domain.enums.MessageQueue;

@Converter
public class MessageQueueConverter implements AttributeConverter<MessageQueue, Integer> {

  public Integer convertToDatabaseColumn(MessageQueue value) {
    if (value == null) {
      return null;
    }

    return value.getId();
  }

  public MessageQueue convertToEntityAttribute(Integer value) {
    if (value == null) {
      return null;
    }

    return MessageQueue.fromId(value);
  }
}
