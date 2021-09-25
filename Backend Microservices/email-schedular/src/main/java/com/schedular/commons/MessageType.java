package com.schedular.commons;

import org.apache.logging.log4j.message.StringFormattedMessage;
import com.schedular.utils.Constant;

public enum MessageType {

  /** The Api request. */
  ApiRequest,
  /** The Api request response. */
  ApiRequestResponse,
  /** The Info. */
  Info,
  /** The Debug. */
  Debug,
  /** The Error. */
  Error;

  public String getLogName(Logger logger) {
    return new StringFormattedMessage("%s - %s - %s", logger.getValue(Constant.REQUEST_KEY),
        this.name(), logger.getValue(Constant.REQUEST_ID)).getFormattedMessage();
  }

}