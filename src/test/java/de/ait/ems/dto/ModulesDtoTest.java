package de.ait.ems.dto;

import de.ait.ems.models.Module;
import java.util.ArrayList;
import java.util.List;
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
@DisplayName("Modules DTO is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class ModulesDtoTest {

  public static final Long ID = 1L;
  public static final String NAME = "Backend";
  public static final Boolean IS_ARCHIVED = false;

  @Nested
  @DisplayName("ModuleDto:")
  public class TestsModuleDto {

    @Test
    public void get_module_dto() {
      ModuleDto moduleDtoNoArg = new ModuleDto();
      Module module = new Module(ID, NAME, IS_ARCHIVED);
      ModuleDto moduleDto = ModuleDto.from(module);

      Assertions.assertNotNull(moduleDtoNoArg);
      Assertions.assertEquals(ID, moduleDto.getId());
      Assertions.assertEquals(NAME, moduleDto.getName());
      Assertions.assertEquals(IS_ARCHIVED, moduleDto.getArchived());
    }

    @Test
    public void get_modules_dto() {
      Module module1 = new Module(ID, NAME, IS_ARCHIVED);
      Module module2 = new Module(ID, NAME, IS_ARCHIVED);
      List<Module> modules = new ArrayList<>();
      modules.add(module1);
      modules.add(module2);
      List<ModuleDto> moduleDtoList = ModuleDto.from(modules);

      Assertions.assertEquals(2, moduleDtoList.size());
    }
  }

  @Nested
  @DisplayName("NewModuleDto:")
  public class TestsNewModuleDto {

    @Test
    public void get_new_module_dto() {
      NewModuleDto newModuleDto = new NewModuleDto();
      newModuleDto.setName(NAME);

      Assertions.assertEquals(NAME, newModuleDto.getName());
    }
  }

  @Nested
  @DisplayName("UpdateModuleDto:")
  public class TestsUpdateModuleDto {

    @Test
    public void get_update_module_dto() {
      UpdateModuleDto updateModuleDtoNoArg = new UpdateModuleDto();
      UpdateModuleDto updateModuleDto = new UpdateModuleDto(NAME, IS_ARCHIVED);

      Assertions.assertNotNull(updateModuleDtoNoArg);
      Assertions.assertEquals(NAME, updateModuleDto.getName());
      Assertions.assertEquals(IS_ARCHIVED, updateModuleDto.getArchived());
    }
  }
}
