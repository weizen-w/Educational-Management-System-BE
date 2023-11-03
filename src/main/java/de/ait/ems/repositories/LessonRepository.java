package de.ait.ems.repositories;

import de.ait.ems.models.Lesson;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

  List<Lesson> findByGroupId(Long groupId);
}