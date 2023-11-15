package de.ait.ems.repositories;

import de.ait.ems.models.Lesson;
import de.ait.ems.models.Submission;
import de.ait.ems.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

  List<Submission> getByStudent(User student);

  List<Submission> getSubmissionsByLesson(Lesson lesson);
}