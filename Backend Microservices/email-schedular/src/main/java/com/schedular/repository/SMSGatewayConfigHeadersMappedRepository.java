package com.schedular.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.schedular.dto.SMSGatewayConfigHeadersMappedDetails;

@Repository
@Transactional
public interface SMSGatewayConfigHeadersMappedRepository
    extends JpaRepository<SMSGatewayConfigHeadersMappedDetails, Long> {

  Optional<SMSGatewayConfigHeadersMappedDetails> findTopByBasicInfoApplicationIdAndBasicInfoStatus(
      Integer appId, Integer status);

  Optional<SMSGatewayConfigHeadersMappedDetails> findOneByIdConfigId(Integer configId);

}
