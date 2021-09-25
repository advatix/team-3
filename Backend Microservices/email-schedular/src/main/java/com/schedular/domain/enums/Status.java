package com.schedular.domain.enums;

import java.util.Objects;

public enum Status {

  ACTIVE(1), INACTIVE(2);

  private Integer id;

  Status(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public static Status fromId(Integer id) {
    if (Objects.isNull(id))
      return null;
    for (Status queue : Status.values()) {
      if (queue.getId().equals(id)) {
        return queue;
      }
    }
    return null;
  }

}
