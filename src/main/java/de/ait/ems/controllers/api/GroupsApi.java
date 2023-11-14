package de.ait.ems.controllers.api;

import de.ait.ems.dto.GroupDto;
import de.ait.ems.dto.LessonDto;
import de.ait.ems.dto.NewGroupDto;
import de.ait.ems.dto.NewLessonDto;
import de.ait.ems.dto.StandardResponseDto;
import de.ait.ems.dto.UpdateGroupDto;
import de.ait.ems.dto.UserDto;
import de.ait.ems.security.details.AuthenticatedUser;
import de.ait.ems.validations.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 17/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@RequestMapping("/api/groups")
@Tags(value = {
    @Tag(name = "Groups", description = "This controller realized management of usersgroups")
})
@Validated
public interface GroupsApi {

  @Operation(summary = "Create a group", description = "Allowed create a new group. Available to administrator")
  @PreAuthorize("hasAuthority('ADMIN')")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201",
          description = "The group was created successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = GroupDto.class))),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDto.class)))
  })
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  GroupDto addGroup(
      @RequestBody @Valid @Parameter(description = "Body with new group", required = true) NewGroupDto newGroup);

  @Operation(summary = "Getting a list of groups", description = "Return list of all groups. Available to administrator")
  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<GroupDto> getGroups();

  @Operation(summary = "Getting a list of groups by authenticated user", description = "Return list of all groups. Available to authenticated user")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STUDENT') or hasAuthority('TEACHER')")
  @GetMapping("/byAuthUser")
  @ResponseStatus(code = HttpStatus.OK)
  List<GroupDto> getGroupsByAuthUser(
      @Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser user);

  @Operation(summary = "Getting a list of users by group", description = "Return list of users from requested group. Available to administrator")
  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping("/{group-id}/students")
  @ResponseStatus(code = HttpStatus.OK)
  List<UserDto> getUsersFromGroup(
      @Parameter(description = "Group ID", example = "1", required = true) @PathVariable("group-id") @Min(1) Long groupId);

  @Operation(summary = "Getting a list of users by group", description = "Return list of users from requested group. Available to administrator")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STUDENT') or hasAuthority('TEACHER')")
  @GetMapping("/{group-id}/users/byMainGroup")
  @ResponseStatus(code = HttpStatus.OK)
  List<UserDto> getUsersFromGroupByMainGroup(
      @Parameter(description = "Group ID", example = "1", required = true) @PathVariable("group-id") @Min(1) Long groupId);

  @Operation(summary = "Getting a group", description = "Return one group by requested group id. Available to users in this group")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STUDENT') or hasAuthority('TEACHER')")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Request processed successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = GroupDto.class))
      ),
      @ApiResponse(responseCode = "404",
          description = "Group not found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = StandardResponseDto.class)))
  })
  @GetMapping("/{group-id}")
  @ResponseStatus(code = HttpStatus.OK)
  GroupDto getGroup(@Parameter(description = "Group ID", example = "1")
  @PathVariable("group-id") @Min(1) Long groupId);

  @Operation(summary = "Group update", description = "Update group info. Available to administrator")
  @PreAuthorize("hasAuthority('ADMIN')")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Update processed successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = GroupDto.class))
      ),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDto.class))
      ),
      @ApiResponse(responseCode = "404",
          description = "Group not found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = StandardResponseDto.class)))
  })
  @PutMapping("/{group-id}")
  @ResponseStatus(code = HttpStatus.OK)
  GroupDto updateGroup(@Parameter(description = "Group ID", example = "1", required = true)
  @PathVariable("group-id") @Min(1) Long groupId, @RequestBody @Valid UpdateGroupDto updateGroup);

  @Operation(summary = "Getting a list of lessons by group", description = "Return list of lessons from requested group. Available to auth user")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('STUDENT') or hasAuthority('TEACHER')")
  @GetMapping("/{group-id}/lessons")
  @ResponseStatus(code = HttpStatus.OK)
  List<LessonDto> getLessonsByGroup(
      @Parameter(description = "Group ID", example = "1", required = true) @PathVariable("group-id") @Min(1) Long groupId);

  @Operation(summary = "Create a lesson", description = "Allowed create a new lesson for existed group. Available to administrator")
  @PreAuthorize("hasAuthority('ADMIN')")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201",
          description = "The lesson was created successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = LessonDto.class))),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDto.class)))
  })
  @PostMapping("/{group-id}/lessons")
  @ResponseStatus(code = HttpStatus.CREATED)
  LessonDto addLesson(
      @RequestBody @Valid @Parameter(description = "Body with new lesson", required = true) NewLessonDto newLesson,
      @Parameter(description = "Group ID", example = "1", required = true) @PathVariable("group-id") @Min(1) Long groupId);

}
