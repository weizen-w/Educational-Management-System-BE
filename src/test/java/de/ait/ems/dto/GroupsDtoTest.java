package de.ait.ems.dto;

import de.ait.ems.models.Course;
import de.ait.ems.models.Group;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * 14/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@DisplayName("Groups DTO is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class GroupsDtoTest {

  public static final Long ID = 1L;
  public static final String NAME = "Cohort-26";
  public static final Long COURSE_ID = 1L;
  public static final Course COURSE = new Course(CoursesDtoTest.ID, CoursesDtoTest.NAME,
      CoursesDtoTest.IS_ARCHIVED);
  public static final Boolean IS_ARCHIVED = false;

  @Nested
  @DisplayName("GroupDto:")
  public class TestsGroupDto {

    @Test
    public void get_group_dto() {
      GroupDto groupDtoNoArg = new GroupDto();
      Group group = new Group(ID, NAME, COURSE, IS_ARCHIVED);
      GroupDto groupDto = GroupDto.from(group);

      Assertions.assertNotNull(groupDtoNoArg);
      Assertions.assertEquals(ID, groupDto.getId());
      Assertions.assertEquals(NAME, groupDto.getName());
      Assertions.assertEquals(COURSE_ID, groupDto.getCourseId());
      Assertions.assertEquals(IS_ARCHIVED, groupDto.getIsArchived());
    }

    @Test
    public void get_groups_dto() {
      Group group1 = new Group(ID, NAME, COURSE, IS_ARCHIVED);
      Group group2 = new Group(ID, NAME, COURSE, IS_ARCHIVED);
      List<Group> groups = new ArrayList<>();
      groups.add(group1);
      groups.add(group2);
      List<GroupDto> groupDtoList = GroupDto.from(groups);

      Assertions.assertEquals(2, groupDtoList.size());
    }
  }

  @Nested
  @DisplayName("NewGroupDto:")
  public class TestsNewGroupDto {

    @Test
    public void get_new_group_dto() {
      NewGroupDto newGroupDto = new NewGroupDto();
      newGroupDto.setName(NAME);
      newGroupDto.setCourseId(COURSE_ID);

      Assertions.assertEquals(NAME, newGroupDto.getName());
      Assertions.assertEquals(COURSE_ID, newGroupDto.getCourseId());
    }
  }

  @Nested
  @DisplayName("UpdateGroupDto:")
  public class TestsUpdateGroupDto {

    @Test
    public void get_update_group_dto() {
      UpdateGroupDto updateGroupDtoNoArg = new UpdateGroupDto();
      UpdateGroupDto updateGroupDto = new UpdateGroupDto(NAME, COURSE_ID, IS_ARCHIVED);

      Assertions.assertNotNull(updateGroupDtoNoArg);
      Assertions.assertEquals(NAME, updateGroupDto.getName());
      Assertions.assertEquals(COURSE_ID, updateGroupDto.getCourseId());
      Assertions.assertEquals(IS_ARCHIVED, updateGroupDto.getIsArchived());
    }
  }
}
