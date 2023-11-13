package de.ait.ems.repositories;

import de.ait.ems.models.Lesson;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

  List<Lesson> findByGroupId(Long groupId);

  List<Lesson> findAllByTeacherId(Long id);

//  @Query("select distinct lesson_id ,lesson0_.group_id ,group2_.group_name ,group2_.archived ,lesson_title ,lesson_description ,lesson_type ,teacher_id ,account2_.first_name ,account2_.last_name ,account2_.email ,account2_.role ,account2_.photo_link ,lesson_date ,start_time ,end_time ,lesson0_.module_id ,link_lms ,link_zoom ,lesson0_.archived from lesson lesson0_  inner join account_group group1_  on lesson0_.group_id=group1_.group_id  inner join student_group group2_  on lesson0_.group_id=group2_.group_id  inner join account account1_  on group1_.account_id=account1_.account_id  inner join account account2_  on lesson0_.teacher_id=account2_.account_id  inner join \"module\" module0_  on lesson0_.module_id=module0_.module_id Where account1_.account_id = :studentId")
//  List<Lesson> getAllByUserId(@Param("studentId") Long userId);
}
