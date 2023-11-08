package de.ait.ems.dto;

import java.io.Serializable;
import lombok.Value;

/**
 * DTO for {@link de.ait.ems.models.Comment}
 */
@Value
public class UpdateCommentDto implements Serializable {

  String messageText;
  Boolean archived;
}