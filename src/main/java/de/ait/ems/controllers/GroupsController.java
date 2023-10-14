package de.ait.ems.controllers;

import de.ait.ems.dto.GroupDto;
import de.ait.ems.dto.NewGroupDto;
import de.ait.ems.dto.StandardResponseDto;
import de.ait.ems.dto.UpdateGroupDto;
import de.ait.ems.services.GroupsService;
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
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 14/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
@Tags(value = {
    @Tag(name = "Groups")
})
public class GroupsController {

  private final GroupsService groupsService;

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
  public ResponseEntity<GroupDto> addGroup(@RequestBody @Valid NewGroupDto newGroup) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(groupsService.addGroup(newGroup));
  }

  @Operation(summary = "Getting a list of groups", description = "Available to administrator")
  @GetMapping
  public ResponseEntity<List<GroupDto>> getGroups() {
    return ResponseEntity.ok(groupsService.getGroups());
  }

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
  public GroupDto getGroup(@Parameter(description = "Group ID", example = "1")
  @PathVariable("group-id") Long groupId) {
    return groupsService.getGroup(groupId);
  }

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
  public GroupDto updateGroup(@Parameter(description = "Group ID", example = "1")
  @PathVariable("group-id") Long groupId, @RequestBody @Valid UpdateGroupDto updateGroup) {
    return groupsService.updateGroup(groupId, updateGroup);
  }

  @Operation(summary = "Group delete", description = "Available to administrator")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Delete processed successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = GroupDto.class))
      ),
      @ApiResponse(responseCode = "404",
          description = "Group not found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = StandardResponseDto.class)))
  })
  @DeleteMapping("/{group-id}")
  public GroupDto deleteGroup(@Parameter(description = "Group ID", example = "1")
  @PathVariable("group-id") Long groupId) {
    return groupsService.deleteGroup(groupId);
  }
}
