/*
 * @author Advatix
 * 
 * @since 2019
 * 
 * @version 1.0
 */
package com.schedular.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class AbstractDomain.
 */
@MappedSuperclass
@JsonIgnoreProperties(value = {"createdOn", "updatedOn"}, allowGetters = true, allowSetters = false)
public abstract class AbstractDomain implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -3213961670407371349L;

  /** The created on. */
  // @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CreatedOn", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
  @CreatedDate
  // @CreationTimestamp
  // @JsonFormat(shape = Shape.NUMBER, timezone = Constant.TIMEZONE)
  private Date createdOn;

  /** The updated on. */
  // @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "UpdatedOn",
      columnDefinition = "DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
  @LastModifiedDate
  // @UpdateTimestamp
  // @JsonFormat(shape = Shape.NUMBER, timezone = Constant.TIMEZONE)
  private Date updatedOn;

  /**
   * Pre update.
   */
  @PreUpdate
  public void preUpdate() {
    // updatedOn = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
    updatedOn = new Date(Calendar.getInstance().getTimeInMillis());
  }

  /**
   * Pre persist.
   */
  @PrePersist
  public void prePersist() {
    Date now = new Date(Calendar.getInstance().getTimeInMillis());
    createdOn = now;
    updatedOn = now;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public Date getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(Date updatedOn) {
    this.updatedOn = updatedOn;
  }

  /**
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("AbstractDomain [");
    if (createdOn != null)
      builder.append("createdOn=").append(createdOn).append(", ");
    if (updatedOn != null)
      builder.append("updatedOn=").append(updatedOn);
    builder.append("]");
    return builder.toString();
  }

  /**
   * Hash code.
   *
   * @return the int
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
    result = prime * result + ((updatedOn == null) ? 0 : updatedOn.hashCode());
    return result;
  }

  /**
   * Equals.
   *
   * @param obj the obj
   * @return true, if successful
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AbstractDomain other = (AbstractDomain) obj;
    if (createdOn == null) {
      if (other.createdOn != null)
        return false;
    } else if (!createdOn.equals(other.createdOn)) {
      return false;
    }
    if (updatedOn == null) {
      if (other.updatedOn != null)
        return false;
    } else if (!updatedOn.equals(other.updatedOn)) {
      return false;
    }
    return true;
  }

  /**
   * Copy entity from.
   *
   * @param source the source
   */
  public void copyEntityFrom(AbstractDomain source) {
    this.createdOn = source.createdOn;
    this.updatedOn = source.updatedOn;
  }

}
