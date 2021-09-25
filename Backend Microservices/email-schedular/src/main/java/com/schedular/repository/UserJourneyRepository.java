/*
 * @author Advatix
 * 
 * @since 2019
 * 
 * @version 1.0
 */
package com.schedular.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.schedular.domain.UserJourney;

/**
 * The Interface MySqlUserJourneyRepository.
 */
@Repository
@Transactional
public interface UserJourneyRepository extends JpaRepository<UserJourney, Long> {

  /**
   * Find by api id.
   *
   * @param apiId the api id
   * @return the optional
   */
  Optional<UserJourney> findByApiId(String apiId);

}
