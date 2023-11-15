package de.ait.ems.dto;

import de.ait.ems.models.Submission;
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
@Nested
@DisplayName("Submissions DTO is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class SubmissionsDtoTest {

  public static final Long SUBMISSION_ID = 1L;
  public static final String DESCRIPTION = "Qwerty007!}";
  public static final Long LESSON_ID = 1L;
  public static final Long STUDENT_ID = 1L;
  public static final String SUBMISSION_STATE = "VIEWED";
  public static final Boolean ARCHIVED = false;


  @Nested
  @DisplayName("SubmissionDto:")
  public class TestsSubmissionDto {

//    @Test
//    public void get_submission_dto() {
//      SubmissionDto SubmissionDtoNoArg = new SubmissionDto();
//      Submission submission = new Submission(SUBMISSION_ID, DESCRIPTION, null
//          , null,
//          Submission.Status.valueOf(SUBMISSION_STATE),
//          ARCHIVED);
//      SubmissionDto submissionDto = de.ait.ems.dto.SubmissionDto.from(submission);
//
//      Assertions.assertNotNull(SubmissionDtoNoArg);
//      Assertions.assertEquals(SUBMISSION_ID, submissionDto.getSubmission_id());
//      Assertions.assertEquals(DESCRIPTION, submissionDto.getDescription());
//      Assertions.assertEquals(SUBMISSION_STATE, submissionDto.getSubmission_state());
//      Assertions.assertEquals(ARCHIVED, submissionDto.archived);
//    }
//
//    @Test
//    public void get_submissions_dto() {
//      Submission submission1 = new Submission(SUBMISSION_ID, DESCRIPTION,
//          null, null,
//          Submission.Status.valueOf(SUBMISSION_STATE),
//          ARCHIVED);
//      Submission submission2 = new Submission(SUBMISSION_ID, DESCRIPTION,
//          null, null,
//          Submission.Status.valueOf(SUBMISSION_STATE),
//          ARCHIVED);
//      List<Submission> submissionsList = new ArrayList<>();
//      submissionsList.add(submission1);
//      submissionsList.add(submission2);
//      List<SubmissionDto> submissionDtoList = SubmissionDto.from(submissionsList);
//
//      Assertions.assertEquals(2, submissionDtoList.size());
//    }
  }

  @Nested
  @DisplayName("NewSubmissionDto:")
  public class TestsNewSubmissionDto {

    @Test
    public void get_new_submission_dto() {
      NewSubmissionDto newSubmissionDto = new NewSubmissionDto();
      newSubmissionDto.setDescription(DESCRIPTION);

      Assertions.assertEquals(DESCRIPTION, newSubmissionDto.getDescription());
    }
  }

  @Nested
  @DisplayName("UpdateSubmissionDto:")
  public class TestsUpdateSubmissionDto {

    @Test
    public void get_update_submission_dto() {
      UpdateSubmissionDto updateSubmissionDtoNoArg = new UpdateSubmissionDto();
      UpdateSubmissionDto UpdateSubmissionDto = new UpdateSubmissionDto(DESCRIPTION,
          Submission.Status.valueOf(SUBMISSION_STATE),
          ARCHIVED);

      Assertions.assertNotNull(updateSubmissionDtoNoArg);
      Assertions.assertEquals(DESCRIPTION, UpdateSubmissionDto.getDescription());
      Assertions.assertEquals(SUBMISSION_STATE, UpdateSubmissionDto.getSubmission_state().name());
      Assertions.assertEquals(ARCHIVED, UpdateSubmissionDto.getArchived());
    }
  }
}
