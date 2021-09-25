package com.schedular.dto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryExecutorRequestDto implements Serializable {

  private static final long serialVersionUID = 9064893202812297707L;

  @NotBlank(message = "Datasource cannot empty")
  private String datasource;

  @NotBlank(message = "Username cannot empty")
  private String username;

  @NotBlank(message = "Password cannot empty")
  private String password;

  @NotBlank(message = "Query string cannot empty")
  private String query;

  public String getDatasource() {
    return datasource;
  }

  public void setDatasource(String datasource) {
    this.datasource = datasource;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  @Override
  public String toString() {
    return "QueryExecutorRequestDto [datasource=" + datasource + ", username=" + username
        + ", password=" + password + ", query=" + query + "]";
  }

}
