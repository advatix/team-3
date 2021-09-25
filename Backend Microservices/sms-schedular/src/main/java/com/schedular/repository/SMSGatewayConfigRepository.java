package com.schedular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.schedular.domain.SMSGatewayConfig;

@Repository
@Transactional
public interface SMSGatewayConfigRepository extends JpaRepository<SMSGatewayConfig, Long> {

}
