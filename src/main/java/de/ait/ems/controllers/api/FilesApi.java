package de.ait.ems.controllers.api;

import de.ait.ems.dto.StandardResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 26/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Tags(value = @Tag(name = "files"))
public interface FilesApi {

  @PostMapping(value = "/api/files", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  StandardResponseDto upload(
      @Parameter(content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE))
      @RequestParam("file") MultipartFile file);

}
