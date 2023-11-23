package de.ait.ems.services;

import static de.ait.ems.dto.UserDto.from;

import de.ait.ems.dto.AttendanceDto;
import de.ait.ems.dto.NewUserDto;
import de.ait.ems.dto.SubmissionDto;
import de.ait.ems.dto.UpdateUserDto;
import de.ait.ems.dto.UserDto;
import de.ait.ems.exceptions.RestException;
import de.ait.ems.mail.MailTemplatesUtil;
import de.ait.ems.mail.TemplateProjectMailSender;
import de.ait.ems.models.Attendance;
import de.ait.ems.models.ConfirmationCode;
import de.ait.ems.models.Submission;
import de.ait.ems.models.User;
import de.ait.ems.models.User.Role;
import de.ait.ems.repositories.AttendanceRepository;
import de.ait.ems.repositories.ConfirmationCodesRepository;
import de.ait.ems.repositories.SubmissionRepository;
import de.ait.ems.repositories.UsersRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @project EducationalManagementSystem
 * @AUTHOR Oleksandr Zhurba on 17.10.2023.
 **/
@RequiredArgsConstructor
@Service
public class UsersService {

  private final UsersRepository usersRepository;
  private final ConfirmationCodesRepository confirmationCodesRepository;
  private final PasswordEncoder passwordEncoder;
  private final TemplateProjectMailSender mailSender;
  private final MailTemplatesUtil mailTemplatesUtil;

  @Value("${base.url}")
  private String baseUrl;
  private final AttendanceRepository attendanceRepository;
  private final SubmissionRepository submissionRepository;

  @Transactional
  public UserDto register(NewUserDto newUser) {
    checkIfExistsByEmail(newUser);
    User user = saveNewUser(newUser);
    String codeValue = UUID.randomUUID().toString();
    saveConfirmCode(user, codeValue);
    String link = createLinkForConfirmation(codeValue);
    String html = mailTemplatesUtil.createConfirmationMail(user.getFirstName(), user.getLastName(),
        link);
    mailSender.send(user.getEmail(), "Registration", html);
    return from(user);
  }

  private String createLinkForConfirmation(String codeValue) {
    return baseUrl + "/confirm/" + codeValue;
  }

  private void saveConfirmCode(User user, String codeValue) {
    ConfirmationCode code = ConfirmationCode.builder()
        .code(codeValue)
        .user(user)
        .expiredDateTime(LocalDateTime.now().plusDays(1))
        .build();
    confirmationCodesRepository.save(code);
  }

  private User saveNewUser(NewUserDto newUser) {
    User user = User.builder()
        .email(newUser.getEmail())
        .hashPassword(passwordEncoder.encode(newUser.getPassword()))
        .role(Role.STUDENT)
        .firstName(newUser.getFirstName())
        .lastName(newUser.getLastName())
        .state(User.State.NOT_CONFIRMED)
        .build();
    usersRepository.save(user);
    return user;
  }

  private void checkIfExistsByEmail(NewUserDto newUser) {
    if (usersRepository.existsByEmail(newUser.getEmail())) {
      throw new RestException(HttpStatus.CONFLICT,
          "User with email <" + newUser.getEmail() + "> already exists");
    }
  }

  public UserDto getUserById(Long currentUserId) {
    return from(getUserOrThrow(currentUserId));
  }

  @Transactional
  public UserDto confirm(String confirmCode) {
    ConfirmationCode code = confirmationCodesRepository
        .findByCodeAndExpiredDateTimeAfter(confirmCode, LocalDateTime.now())
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Code not found or is expired"));
    User user = usersRepository
        .findFirstByCodesContains(code)
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "User by code not found"));
    user.setState(User.State.CONFIRMED);
    usersRepository.save(user);
    return UserDto.from(user);
  }

  public List<UserDto> getAllUsers() {
    List<User> users = usersRepository.findAll();
    return from(users);
  }

  public List<UserDto> getUsersByRole(Role role) {
    List<User> users = usersRepository.findAllByRole(role);
    return from(users);
  }

  public UserDto updateUser(Long userId, UpdateUserDto updateUser) {
    User userForUpdate = getUserOrThrow(userId);
    if (updateUser.getPassword() != null) {
      userForUpdate.setHashPassword(passwordEncoder.encode(updateUser.getPassword()));
    }
    if (updateUser.getFirstName() != null) {
      userForUpdate.setFirstName(updateUser.getFirstName());
    }
    if (updateUser.getLastName() != null) {
      userForUpdate.setLastName(updateUser.getLastName());
    }
    if (updateUser.getEmail() != null) {
      userForUpdate.setEmail(updateUser.getEmail());
    }
    if (updateUser.getRole() != null) {
      userForUpdate.setRole(updateUser.getRole());
    }
    if (updateUser.getState() != null) {
      userForUpdate.setState(updateUser.getState());
    }
    if (updateUser.getPhotoLink() != null) {
      userForUpdate.setPhotoLink(updateUser.getPhotoLink());
    }
    usersRepository.save(userForUpdate);
    return from(userForUpdate);
  }

  public User getUserOrThrow(Long userId) {
    return usersRepository.findById(userId).orElseThrow(
        () -> new RestException(HttpStatus.NOT_FOUND,
            "User with id <" + userId + "> not found"));
  }

  public List<AttendanceDto> getAttendanceByUserId(Long userId) {
    User student = getUserOrThrow(userId);
    if (student != null) {
      List<Attendance> attendanceList = attendanceRepository.getAttendanceByStudent(student);
      return AttendanceDto.from(attendanceList);
    } else {
      return null;
    }
  }

  public List<SubmissionDto> getSubmissionsByUserId(Long userId) {
    User student = getUserOrThrow(userId);
    if (student != null) {
      List<Submission> submissionList = submissionRepository.getByStudent(student);
      return SubmissionDto.from(submissionList);
    }
    return null;
  }
}
