package com.schedular.domain.enums;

import java.util.Objects;

public enum MessageQueue {

  INQUEUE(1), COMPLETE(2), NEXTATTEMPT(3), FAILED(4);

  private Integer id;

  MessageQueue(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public static MessageQueue fromId(Integer id) {
    if (Objects.isNull(id))
      return null;
    for (MessageQueue queue : MessageQueue.values()) {
      if (queue.getId().equals(id)) {
        return queue;
      }
    }
    return null;
  }

}
