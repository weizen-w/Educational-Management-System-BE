package de.ait.ems.services;

import de.ait.ems.dto.SubmissionDto;
import de.ait.ems.dto.UpdateSubmissionDto;
import de.ait.ems.exceptions.RestException;
import de.ait.ems.mapper.EntityMapper;
import de.ait.ems.models.Submission;
import de.ait.ems.repositories.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Oleksandr Zhurba on 07.11.2023.
 * @project Educational-Management-System-BE
 **/
@RequiredArgsConstructor
@Service
public class SubmissionsService {

  private final SubmissionRepository submissionRepository;
  private final EntityMapper entityMapper;

  public SubmissionDto updateSubmission(Long submissionId, UpdateSubmissionDto updateSubmissionDto) {
    Submission submissionUpdate = getSubmissionOrThrow(submissionId);
    if (updateSubmissionDto.getArchived() !=null){
      submissionUpdate.setArchived(updateSubmissionDto.getArchived());
    }
    if (updateSubmissionDto.getDescription() !=null){
      submissionUpdate.setDescription(updateSubmissionDto.getDescription());
    }
    if (updateSubmissionDto.getState() !=null){
      submissionUpdate.setState(updateSubmissionDto.getState());
    }
    submissionRepository.save(submissionUpdate);
    return entityMapper.convertToDto(submissionUpdate);
  }

  public SubmissionDto getById(Long submissionId) {
    return entityMapper.convertToDto(getSubmissionOrThrow(submissionId));
  }

  public Submission getSubmissionOrThrow(Long submissionId) {
    return submissionRepository.findById(submissionId).orElseThrow(
        () -> new RestException(HttpStatus.NOT_FOUND,
            "Submission with id <" + submissionId + "> not found"));
  }
}
