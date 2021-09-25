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

@Entity
@Table(name = MasterQueueStatus.TABLE_NAME)
public class MasterQueueStatus implements Serializable {

  private static final long serialVersionUID = -4700399871924215612L;

  public static final String TABLE_NAME = "Master_Queue_Status";

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
