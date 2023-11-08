package de.ait.ems.repositories;

import de.ait.ems.models.Comment;
import de.ait.ems.models.Submission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

  List<Comment> getBySubmission(Submission submission);
}