package de.ait.ems.controllers;

import de.ait.ems.controllers.api.ModulesApi;
import de.ait.ems.dto.ModuleDto;
import de.ait.ems.dto.NewModuleDto;
import de.ait.ems.dto.UpdateModuleDto;
import de.ait.ems.services.ModulesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * 01/11/2023 EducationalManagementSystemBE
 *
 * @author Wladimir Weizen
 */
@RestController
@RequiredArgsConstructor
public class ModulesController implements ModulesApi {

  private final ModulesService modulesService;

  @Override
  public ModuleDto addModule(NewModuleDto newModule) {
    return modulesService.addModule(newModule);
  }

  @Override
  public List<ModuleDto> getModules() {
    return modulesService.getModules();
  }

  @Override
  public ModuleDto updateModule(Long moduleId, UpdateModuleDto updateModule) {
    return modulesService.updateModule(moduleId, updateModule);
  }
}
