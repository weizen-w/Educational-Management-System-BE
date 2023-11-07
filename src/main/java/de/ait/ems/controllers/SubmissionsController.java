package de.ait.ems.controllers;

import de.ait.ems.controllers.api.SubmissionsApi;
import de.ait.ems.dto.SubmissionDto;
import de.ait.ems.dto.UpdateSubmissionDto;
import de.ait.ems.services.SubmissionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Oleksandr Zhurba on 07.11.2023.
 * @project Educational-Management-System-BE
 **/
@RestController
@RequiredArgsConstructor
public class SubmissionsController implements SubmissionsApi {

  private final SubmissionsService submissionsService;
  @Override
  public SubmissionDto updateSubmission(Long submissionId,
      UpdateSubmissionDto updateSubmissionDto) {
    return submissionsService.updateSubmission(submissionId,updateSubmissionDto);
  }

  @Override
  public SubmissionDto getSubmission(Long submissionId) {
    return submissionsService.getById(submissionId);
  }
}
