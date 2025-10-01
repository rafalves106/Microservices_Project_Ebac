/**
 * @author falvesmac
 */

package com.produtos.produtos_service.handler;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class ApiError {

  private int status;
  private String error;
  private String message;
  private LocalDate timestamp;
  private Map<String, String> errors;

  public ApiError(int status, String error, String message, LocalDate timestamp) {
    this.status = status;
    this.error = error;
    this.message = message;
    this.timestamp = timestamp;
    this.errors = null;
  }

  public ApiError(int status, String error, String message, LocalDate timestamp, Map<String, String> errors) {
    this.status = status;
    this.error = error;
    this.message = message;
    this.timestamp = timestamp;
    this.errors = errors;
  }

}