package de.ait.ems.controllers.api;

import de.ait.ems.dto.GroupDto;
import de.ait.ems.dto.NewGroupDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @Tag(name = "Groups")
})
public interface GroupsApi {

  @Operation(summary = "Create a group", description = "Available to administrator")
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
  GroupDto addGroup(@RequestBody @Valid NewGroupDto newGroup);

  @Operation(summary = "Getting a list of groups", description = "Available to administrator")
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<GroupDto> getGroups();

  @Operation(summary = "Getting a list of groups by authenticated user", description = "Available to authenticated user")
  @GetMapping("/byAuthUser")
  @ResponseStatus(code = HttpStatus.OK)
  List<GroupDto> getGroupsByAuthUser(
      @Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser user);

  @Operation(summary = "Getting a list of users by group", description = "Available to administrator")
  @GetMapping("/{group-id}/students")
  @ResponseStatus(code = HttpStatus.OK)
  List<UserDto> getUsersFromGroup(
      @Parameter(description = "Group ID", example = "1") @PathVariable("group-id") Long groupId);

  @Operation(summary = "Getting a group", description = "Available to users in this group")
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
  @PathVariable("group-id") Long groupId);

  @Operation(summary = "Group update", description = "Available to administrator")
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
  GroupDto updateGroup(@Parameter(description = "Group ID", example = "1")
  @PathVariable("group-id") Long groupId, @RequestBody @Valid UpdateGroupDto updateGroup);
}
