package de.ait.ems.controllers;

import de.ait.ems.controllers.api.CommentsApi;
import de.ait.ems.dto.CommentDto;
import de.ait.ems.dto.UpdateCommentDto;
import de.ait.ems.services.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Oleksandr Zhurba on 08.11.2023.
 * @project Educational-Management-System-BE
 **/
@RequiredArgsConstructor
@RestController
public class CommentsController implements CommentsApi {
  private final CommentsService commentsService;
  @Override
  public CommentDto updateComment(Long commentId, UpdateCommentDto updateCommentDto) {
    return commentsService.updateComment(commentId,updateCommentDto);
  }
}
