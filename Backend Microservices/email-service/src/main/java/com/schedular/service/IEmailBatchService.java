package com.schedular.service;

import com.schedular.dto.BatchRequest;
import com.schedular.exceptions.EntityNotFoundException;

public interface IEmailBatchService {

  String createEmailBatch(BatchRequest batchRequest) throws EntityNotFoundException;

}
