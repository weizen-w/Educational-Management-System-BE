package de.ait.ems.exceptions;

import org.springframework.http.HttpStatus;

/**
 * 08/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
public class RestException extends RuntimeException {

  private final HttpStatus status;

  public RestException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
