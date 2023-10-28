package de.ait.ems.controllers;

import de.ait.ems.controllers.api.FilesApi;
import de.ait.ems.dto.StandardResponseDto;
import de.ait.ems.services.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 26/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@RequiredArgsConstructor
@RestController
public class FilesController implements FilesApi {

  private final FilesService filesService;

  @Override
  public StandardResponseDto upload(MultipartFile file) {
    return filesService.upload(file);
  }
}
