package de.ait.ems.dto;

import de.ait.ems.models.ConfirmationCode;
import de.ait.ems.models.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * 01/11/2023 EducationalManagementSystemBE
 *
 * @author Wladimir Weizen
 */
@DisplayName("Users DTO is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class UsersDtoTest {

  public static final Long ID = 1L;
  public static final String PASSWORD = "Qwerty007!}";
  public static final String FIRSTNAME = "Della";
  public static final String LASTNAME = "van den Berg";
  public static final String EMAIL = "zhurba@it-f.com.ua";
  public static final String ROLE = "STUDENT";
  public static final String STATE = "NOT_CONFIRMED";
  public static final String PHOTOLINK =
      "https://breed-assets.wisdompanel.com/cat/sphynx/Sphynx_Cat.png";

  public static final Set<ConfirmationCode> CODES = new HashSet<>();

  @Nested
  @DisplayName("UserDto:")
  public class TestsUserDto {

    @Test
    public void get_user_dto() {
      UserDto userDtoNoArg = new UserDto();
      User user = new User(ID, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, User.Role.valueOf(ROLE),
          User.State.valueOf(STATE), PHOTOLINK, CODES);
      UserDto userDto = UserDto.from(user);

      Assertions.assertNotNull(userDtoNoArg);
      Assertions.assertEquals(ID, userDto.getId());
      Assertions.assertEquals(PASSWORD, userDto.getPassword());
      Assertions.assertEquals(FIRSTNAME, userDto.getFirstName());
      Assertions.assertEquals(LASTNAME, userDto.getLastName());
      Assertions.assertEquals(EMAIL, userDto.getEmail());
      Assertions.assertEquals(ROLE, userDto.getRole());
      Assertions.assertEquals(STATE, userDto.getState());
      Assertions.assertEquals(PHOTOLINK, userDto.getPhotoLink());
    }

    @Test
    public void get_users_dto() {
      User user1 = new User(ID, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, User.Role.valueOf(ROLE),
          User.State.valueOf(STATE), PHOTOLINK, CODES);
      User user2 = new User(ID, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, User.Role.valueOf(ROLE),
          User.State.valueOf(STATE), PHOTOLINK, CODES);
      List<User> usersList = new ArrayList<>();
      usersList.add(user1);
      usersList.add(user2);
      List<UserDto> userDtoList = UserDto.from(usersList);

      Assertions.assertEquals(2, userDtoList.size());
    }
  }

  @Nested
  @DisplayName("NewUserDto:")
  public class TestsNewUserDto {

    @Test
    public void get_new_user_dto() {
      NewUserDto newUserDto = new NewUserDto();
      newUserDto.setFirstName(FIRSTNAME);
      newUserDto.setLastName(LASTNAME);
      newUserDto.setEmail(EMAIL);
      newUserDto.setPassword(PASSWORD);

      Assertions.assertEquals(FIRSTNAME, newUserDto.getFirstName());
      Assertions.assertEquals(LASTNAME, newUserDto.getLastName());
      Assertions.assertEquals(EMAIL, newUserDto.getEmail());
      Assertions.assertEquals(PASSWORD, newUserDto.getPassword());
    }
  }

  @Nested
  @DisplayName("UpdateUserDto:")
  public class TestsUpdateUserDto {

    @Test
    public void get_update_user_dto() {
      UpdateUserDto updateUserDtoNoArg = new UpdateUserDto();
      UpdateUserDto UpdateUserDto = new UpdateUserDto(PASSWORD, FIRSTNAME, LASTNAME, EMAIL,
          User.Role.valueOf(ROLE), User.State.valueOf(STATE), PHOTOLINK);

      Assertions.assertNotNull(updateUserDtoNoArg);
      Assertions.assertEquals(PASSWORD, UpdateUserDto.getPassword());
      Assertions.assertEquals(FIRSTNAME, UpdateUserDto.getFirstName());
      Assertions.assertEquals(LASTNAME, UpdateUserDto.getLastName());
      Assertions.assertEquals(EMAIL, UpdateUserDto.getEmail());
      Assertions.assertEquals(ROLE, UpdateUserDto.getRole().name());
      Assertions.assertEquals(STATE, UpdateUserDto.getState().name());
      Assertions.assertEquals(PHOTOLINK, UpdateUserDto.getPhotoLink());
    }
  }
}
