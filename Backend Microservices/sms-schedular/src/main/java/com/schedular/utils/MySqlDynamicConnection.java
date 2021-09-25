package com.schedular.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Objects;
import com.schedular.commons.Logger;
import com.schedular.exceptions.IllegalArgumentException;

public class MySqlDynamicConnection {

  private Logger log = Logger.getLogger(getClass());

  /**
   * url = "jdbc:mysql://localhost:3306/test1"; user = "root"; password = "secret";
   * 
   * @param url
   * @param userName
   * @param password
   * @throws IllegalArgumentException
   */
  public void connect(String url, String userName, String password, String query)
      throws IllegalArgumentException {
    try (Connection conn = DriverManager.getConnection(url, userName, password)) {
      log.info("Connected to the database");
      executeQuery(conn, query);
    } catch (Exception ex) {
      log.error("An error occurred. Maybe user/password is invalid");
      log.error(ex.getLocalizedMessage(), ex);
      throw new IllegalArgumentException("DB Connection Error: " + ex.getLocalizedMessage());
    }
  }

  public void executeQuery(Connection conn, String query) throws IllegalArgumentException {
    if (Objects.nonNull(conn)) {
      try (ResultSet rs = conn.prepareStatement(query).executeQuery()) {
        while (rs.next()) {
          log.info(String.valueOf(rs.getObject(1)));
        }
      } catch (Exception e) {
        log.error(e.getLocalizedMessage(), e);
        throw new IllegalArgumentException("Statement Execution Error: " + e.getLocalizedMessage());
      }
    }
  }

}
