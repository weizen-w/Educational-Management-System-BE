package de.ait.ems.validations.handler;

import de.ait.ems.validations.dto.ValidationErrorDTO;
import de.ait.ems.validations.dto.ValidationErrorsDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 08/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@ControllerAdvice
public class ValidationExceptionHandler {

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorsDTO> handleValidationException(
      MethodArgumentNotValidException e) {
    List<ValidationErrorDTO> validationErrors = new ArrayList<>();
    List<ObjectError> errors = e.getBindingResult().getAllErrors();
    for (ObjectError error : errors) {
      FieldError fieldError = (FieldError) error;
      ValidationErrorDTO errorDto = ValidationErrorDTO.builder()
          .field(fieldError.getField())
          .message(fieldError.getDefaultMessage())
          .build();
      if (fieldError.getRejectedValue() != null) {
        errorDto.setRejectedValue(fieldError.getRejectedValue().toString());
      }
      validationErrors.add(errorDto);
    }
    return ResponseEntity
        .badRequest()
        .body(ValidationErrorsDTO.builder()
            .errors(validationErrors)
            .build());
  }
}
