package com.schedular.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.schedular.domain.SMTPConfig;

@Repository
@Transactional
public interface SmtpConfigRepository extends JpaRepository<SMTPConfig, Long> {

  Optional<SMTPConfig> findOneByConfigId(Integer configId);

  Optional<SMTPConfig> findTopByApplicationIdAndStatus(Integer applicationId, Integer status);

}
