package de.ait.ems.exceptions;

import de.ait.ems.dto.StandardResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 08/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(value = RestException.class)
  public ResponseEntity<StandardResponseDto> handleRestException(RestException e) {
    return ResponseEntity
        .status(e.getStatus())
        .body(StandardResponseDto.builder()
            .message(e.getMessage())
            .build());
  }
}
