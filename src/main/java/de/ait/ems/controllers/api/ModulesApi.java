package de.ait.ems.controllers.api;

import de.ait.ems.dto.CourseDto;
import de.ait.ems.dto.ModuleDto;
import de.ait.ems.dto.NewModuleDto;
import de.ait.ems.dto.StandardResponseDto;
import de.ait.ems.dto.UpdateModuleDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 01/11/2023 EducationalManagementSystemBE
 *
 * @author Wladimir Weizen
 */
@RequestMapping("/api/modules")
@Tags(value = {
    @Tag(name = "Modules")
})
public interface ModulesApi {

  @Operation(summary = "Create a module", description = "Available to administrator")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201",
          description = "The module was created successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = CourseDto.class))),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDto.class)))
  })
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('ADMIN')")
  ModuleDto addModule(
      @Parameter(description = "Body with new module", required = true) @RequestBody @Valid NewModuleDto newModule);

  @Operation(summary = "Getting a list of modules", description = "Available to administrator")
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  @PreAuthorize("hasAuthority('ADMIN')")
  List<ModuleDto> getModules();

  @Operation(summary = "Module update", description = "Available to administrator")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Update processed successfully",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = CourseDto.class))
      ),
      @ApiResponse(responseCode = "400",
          description = "Validation error",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ValidationErrorsDto.class))
      ),
      @ApiResponse(responseCode = "404",
          description = "Module not found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = StandardResponseDto.class)))
  })
  @PutMapping("/{module-id}")
  @ResponseStatus(code = HttpStatus.OK)
  @PreAuthorize("hasAuthority('ADMIN')")
  ModuleDto updateModule(
      @Parameter(description = "Module ID", example = "1", required = true) @Min(1) @PathVariable("module-id") Long moduleId,
      @Parameter(description = "Body with new module", required = true) @Valid @RequestBody UpdateModuleDto updateModule);
}
