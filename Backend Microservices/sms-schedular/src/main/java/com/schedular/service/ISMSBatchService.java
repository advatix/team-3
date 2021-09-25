package com.schedular.service;

import com.schedular.dto.BatchRequest;
import com.schedular.exceptions.EntityNotFoundException;

public interface ISMSBatchService {

  String createBatch(BatchRequest batchRequest) throws EntityNotFoundException;

  String createEmailBatch(BatchRequest batchRequest) throws EntityNotFoundException;

}
