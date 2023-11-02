package de.ait.ems.services;

import static de.ait.ems.dto.ModuleDto.from;

import de.ait.ems.dto.ModuleDto;
import de.ait.ems.dto.NewModuleDto;
import de.ait.ems.dto.UpdateModuleDto;
import de.ait.ems.exceptions.RestException;
import de.ait.ems.models.Module;
import de.ait.ems.repositories.ModulesRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * 01/11/2023 EducationalManagementSystemBE
 *
 * @author Wladimir Weizen
 */
@RequiredArgsConstructor
@Service
public class ModulesService {

  private final ModulesRepository modulesRepository;

  public ModuleDto addModule(NewModuleDto newModule) {
    Module module = Module.builder()
        .name(newModule.getName())
        .archived(false)
        .build();
    modulesRepository.save(module);
    return from(module);
  }

  public List<ModuleDto> getModules() {
    List<Module> modules = modulesRepository.findAll();
    return from(modules);
  }

  public ModuleDto updateModule(Long moduleId, UpdateModuleDto updateModule) {
    Module moduleForUpdate = getModuleOrThrow(moduleId);
    if (updateModule.getName() != null) {
      moduleForUpdate.setName(updateModule.getName());
    }
    if (updateModule.getArchived() != null) {
      moduleForUpdate.setArchived(updateModule.getArchived());
    }
    modulesRepository.save(moduleForUpdate);
    return from(moduleForUpdate);
  }

  private Module getModuleOrThrow(Long moduleId) {
    return modulesRepository.findById(moduleId).orElseThrow(
        () -> new RestException(HttpStatus.NOT_FOUND,
            "Module with id <" + moduleId + "> not found"));
  }
}
