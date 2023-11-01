package de.ait.ems.services;

import de.ait.ems.dto.GroupDto;
import de.ait.ems.dto.GroupsDtoTest;
import de.ait.ems.dto.NewGroupDto;
import de.ait.ems.dto.UpdateGroupDto;
import de.ait.ems.exceptions.RestException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

/**
 * 14/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@SpringBootTest
@Nested
@DisplayName("Group service is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class GroupsServiceTest {

  @Autowired
  private GroupsService groupsService;

  @Test
  @Sql(scripts = "/sql/data.sql")
  @Transactional
  public void add_group() {
    NewGroupDto newGroupDto = new NewGroupDto();
    newGroupDto.setName(GroupsDtoTest.NAME);
    newGroupDto.setCourseId(GroupsDtoTest.COURSE_ID);
    GroupDto groupDto = groupsService.addGroup(newGroupDto);

    Assertions.assertNotNull(groupDto);
    Assertions.assertEquals(5, groupDto.getId());
    Assertions.assertEquals(newGroupDto.getName(), groupDto.getName());
    Assertions.assertEquals(newGroupDto.getCourseId(), groupDto.getCourseId());
    Assertions.assertEquals(false, groupDto.getArchived());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  @Transactional
  public void get_group() {
    GroupDto groupDto = groupsService.getGroup(GroupsDtoTest.ID);

    Assertions.assertNotNull(groupDto);
    Assertions.assertEquals(GroupsDtoTest.ID, groupDto.getId());
    Assertions.assertEquals("Group 1", groupDto.getName());
    Assertions.assertEquals(1, groupDto.getCourseId());
    Assertions.assertEquals(false, groupDto.getArchived());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  @Transactional
  public void get_groups() {
    List<GroupDto> groups = groupsService.getGroups();

    Assertions.assertNotNull(groups);
    Assertions.assertEquals(4, groups.size());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  @Transactional
  public void update_group() {
    UpdateGroupDto updateGroupDto = new UpdateGroupDto(GroupsDtoTest.NAME, GroupsDtoTest.COURSE_ID,
        GroupsDtoTest.IS_ARCHIVED);
    GroupDto groupDtoAfterUpdate = groupsService.updateGroup(GroupsDtoTest.ID, updateGroupDto);

    Assertions.assertNotNull(groupDtoAfterUpdate);
    Assertions.assertEquals(GroupsDtoTest.ID, groupDtoAfterUpdate.getId());
    Assertions.assertEquals(updateGroupDto.getName(), groupDtoAfterUpdate.getName());
    Assertions.assertEquals(updateGroupDto.getCourseId(), groupDtoAfterUpdate.getCourseId());
    Assertions.assertEquals(updateGroupDto.getArchived(), groupDtoAfterUpdate.getArchived());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  @Transactional
  public void get_exception_for_not_exist_group() {
    Long notExistGroupId = 999L;

    Assertions.assertThrows(RestException.class, () -> groupsService.getGroup(notExistGroupId));
  }
}
