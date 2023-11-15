package de.ait.ems.services;

import de.ait.ems.dto.AttendanceDto;
import de.ait.ems.dto.CoursesDtoTest;
import de.ait.ems.dto.NewUserDto;
import de.ait.ems.dto.SubmissionDto;
import de.ait.ems.dto.UpdateUserDto;
import de.ait.ems.dto.UserDto;
import de.ait.ems.dto.UsersDtoTest;
import de.ait.ems.models.User;
import de.ait.ems.models.User.Role;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

/**
 * 01/11/2023 EducationalManagementSystemBE
 *
 * @author Wladimir Weizen
 */
@SpringBootTest
@Nested
@DisplayName("User service is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class UsersServiceTest {

  @Autowired
  private UsersService usersService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  @Sql(scripts = "/sql/data.sql")
  public void add_user() {
    NewUserDto newUserDto = new NewUserDto();
    newUserDto.setFirstName(UsersDtoTest.FIRSTNAME);
    newUserDto.setLastName(UsersDtoTest.LASTNAME);
    newUserDto.setPassword(UsersDtoTest.PASSWORD);
    newUserDto.setEmail(UsersDtoTest.EMAIL);
    UserDto userDto = usersService.register(newUserDto);

    Assertions.assertNotNull(userDto);
    Assertions.assertEquals(5, userDto.getId());
    Assertions.assertEquals(newUserDto.getFirstName(), userDto.getFirstName());
    Assertions.assertEquals(newUserDto.getLastName(), userDto.getLastName());
    Assertions.assertEquals(newUserDto.getEmail(), userDto.getEmail());
    Assertions.assertTrue(passwordEncoder.matches(newUserDto.getPassword(), userDto.getPassword()));
    Assertions.assertEquals(UsersDtoTest.ROLE, userDto.getRole());
    Assertions.assertEquals(UsersDtoTest.STATE, userDto.getState());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  public void get_users() {
    List<UserDto> users = usersService.getAllUsers();

    Assertions.assertNotNull(users);
    Assertions.assertEquals(4, users.size());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  public void get_users_by_role() {
    List<UserDto> users = usersService.getUsersByRole(Role.valueOf(UsersDtoTest.ROLE));

    Assertions.assertNotNull(users);
    Assertions.assertEquals(3, users.size());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  public void update_user() {
    UpdateUserDto updateUserDto = new UpdateUserDto(UsersDtoTest.PASSWORD, UsersDtoTest.FIRSTNAME,
        UsersDtoTest.LASTNAME, UsersDtoTest.EMAIL,
        User.Role.valueOf(UsersDtoTest.ROLE), User.State.valueOf(UsersDtoTest.STATE),
        UsersDtoTest.PHOTOLINK);
    UserDto userDtoAfterUpdate = usersService.updateUser(UsersDtoTest.ID,
        updateUserDto);

    Assertions.assertNotNull(userDtoAfterUpdate);
    Assertions.assertEquals(CoursesDtoTest.ID, userDtoAfterUpdate.getId());
    Assertions.assertEquals(updateUserDto.getFirstName(), userDtoAfterUpdate.getFirstName());
    Assertions.assertEquals(updateUserDto.getLastName(), userDtoAfterUpdate.getLastName());
    Assertions.assertEquals(updateUserDto.getEmail(), userDtoAfterUpdate.getEmail());
    Assertions.assertTrue(
        passwordEncoder.matches(updateUserDto.getPassword(), userDtoAfterUpdate.getPassword()));
    Assertions.assertEquals(updateUserDto.getRole().name(), userDtoAfterUpdate.getRole());
    Assertions.assertEquals(updateUserDto.getState().name(), userDtoAfterUpdate.getState());
    Assertions.assertEquals(updateUserDto.getPhotoLink(), userDtoAfterUpdate.getPhotoLink());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  public void get_attendance_by_user_id() {
    List<AttendanceDto> attendanceDtoList1 = usersService.getAttendanceByUserId(UsersDtoTest.ID);

    Assertions.assertNotNull(attendanceDtoList1);
    Assertions.assertEquals(10, attendanceDtoList1.size());
    List<AttendanceDto> attendanceDtoList2 = usersService.getAttendanceByUserId(
        UsersDtoTest.ID + 1);

    Assertions.assertNotNull(attendanceDtoList2);
    Assertions.assertEquals(0, attendanceDtoList2.size());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  public void get_submissions_by_user_id() {
    List<SubmissionDto> submissionDtoList1 = usersService.getSubmissionsByUserId(UsersDtoTest.ID);

    Assertions.assertNotNull(submissionDtoList1);
    Assertions.assertEquals(6, submissionDtoList1.size());
    List<SubmissionDto> submissionDtoList2 = usersService.getSubmissionsByUserId(
        UsersDtoTest.ID + 1);

    Assertions.assertNotNull(submissionDtoList2);
    Assertions.assertEquals(0, submissionDtoList2.size());
  }

}
