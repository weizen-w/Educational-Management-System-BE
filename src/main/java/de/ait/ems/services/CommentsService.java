package de.ait.ems.services;

import de.ait.ems.dto.CommentDto;
import de.ait.ems.dto.UpdateCommentDto;
import de.ait.ems.exceptions.RestException;
import de.ait.ems.models.Comment;
import de.ait.ems.repositories.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Oleksandr Zhurba on 08.11.2023.
 * @project Educational-Management-System-BE
 **/
@RequiredArgsConstructor
@Service
public class CommentsService {
  private final CommentsRepository commentsRepository;

  public CommentDto updateComment(Long commentId, UpdateCommentDto updateCommentDto) {
    Comment comment = getCommentOrThrow(commentId);
    if (comment!=null){
      if(updateCommentDto.getArchived()!=null){
        comment.setArchived(updateCommentDto.getArchived());
      }
      if(updateCommentDto.getMessageText()!=null){
        comment.setMessageText(updateCommentDto.getMessageText());
      }
      return CommentDto.from(comment);
    }
    return null;
  }

  public Comment getCommentOrThrow(Long commentId) {
    return commentsRepository.findById(commentId).orElseThrow(
        () -> new RestException(HttpStatus.NOT_FOUND,
            "Comment with id <" + commentId + "> not found"));
  }

  public CommentDto deleteComment(Long commentId) {
    Comment comment = getCommentOrThrow(commentId);
    if (comment!=null){
      commentsRepository.delete(comment);
      return CommentDto.from(comment);
    }
    return null;
  }
}
