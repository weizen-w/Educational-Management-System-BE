package de.ait.ems.services;

import de.ait.ems.dto.CommentDto;
import de.ait.ems.dto.NewCommentDto;
import de.ait.ems.dto.SubmissionDto;
import de.ait.ems.dto.UpdateSubmissionDto;
import de.ait.ems.exceptions.RestException;
import de.ait.ems.models.Comment;
import de.ait.ems.models.Submission;
import de.ait.ems.repositories.CommentsRepository;
import de.ait.ems.repositories.SubmissionRepository;
import de.ait.ems.security.details.AuthenticatedUser;
import java.time.LocalDateTime;
import java.util.List;
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
  private final UsersService usersService;
  private final CommentsRepository commentsRepository;

  public SubmissionDto updateSubmission(Long submissionId,
      UpdateSubmissionDto updateSubmissionDto) {
    Submission submissionUpdate = getSubmissionOrThrow(submissionId);
    if (updateSubmissionDto.getArchived() != null) {
      submissionUpdate.setArchived(updateSubmissionDto.getArchived());
    }
    if (updateSubmissionDto.getDescription() != null) {
      submissionUpdate.setDescription(updateSubmissionDto.getDescription());
    }
    if (updateSubmissionDto.getSubmission_state() != null) {
      submissionUpdate.setState(updateSubmissionDto.getSubmission_state());
    }
    submissionRepository.save(submissionUpdate);
    return SubmissionDto.from(submissionUpdate);
  }

  public SubmissionDto getById(Long submissionId) {
    return SubmissionDto.from(getSubmissionOrThrow(submissionId));
  }

  public Submission getSubmissionOrThrow(Long submissionId) {
    return submissionRepository.findById(submissionId).orElseThrow(
        () -> new RestException(HttpStatus.NOT_FOUND,
            "Submission with id <" + submissionId + "> not found"));
  }

  public List<CommentDto> getCommentsBySubmissionId(Long submissionId) {
    Submission submission = getSubmissionOrThrow(submissionId);
    if (submission != null) {
      List<Comment> commentList = commentsRepository.getBySubmission(submission);
      return CommentDto.from(commentList);
    }
    return null;
  }

  public CommentDto addCommentToSubmission(NewCommentDto newCommentDto, Long submissionId,
      AuthenticatedUser authenticatedUser) {
    Submission submission = getSubmissionOrThrow(submissionId);
    if (submission != null) {
      Comment newComment = Comment
          .builder()
          .messageText(newCommentDto.getMessageText())
          .archived(false)
          .author(usersService.getUserOrThrow(authenticatedUser.getId()))
          .submission(submission)
          .messageDate(LocalDateTime.now())
          .build();
      commentsRepository.save(newComment);
      return CommentDto.from(newComment);
    }
    return null;
  }
}
