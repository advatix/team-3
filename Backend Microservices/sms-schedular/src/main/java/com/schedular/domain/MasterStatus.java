/*
 * @author Advatix
 * 
 * @since 2019
 * 
 * @version 1.0
 */
package com.schedular.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class MasterStatus.
 */
@Entity
@Table(name = MasterStatus.TABLE_NAME)
public class MasterStatus implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -9131883736939272056L;

  public static final String TABLE_NAME = "Master_Status";

  /** The id. */
  @Id
  @Column(name = "Id", columnDefinition = "int(5)")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /** The status desc. */
  @Column(name = "StatusDesc", columnDefinition = "varchar(20)")
  private String statusDesc;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStatusDesc() {
    return statusDesc;
  }

  public void setStatusDesc(String statusDesc) {
    this.statusDesc = statusDesc;
  }

  @Override
  public String toString() {
    return "MasterStatus [id=" + id + ", statusDesc=" + statusDesc + "]";
  }

}
