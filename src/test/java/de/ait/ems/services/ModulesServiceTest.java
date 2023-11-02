package de.ait.ems.services;

import de.ait.ems.dto.CoursesDtoTest;
import de.ait.ems.dto.ModuleDto;
import de.ait.ems.dto.ModulesDtoTest;
import de.ait.ems.dto.NewModuleDto;
import de.ait.ems.dto.UpdateModuleDto;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

/**
 * 01/11/2023 EducationalManagementSystemBE
 *
 * @author Wladimir Weizen
 */
@SpringBootTest
@Nested
@DisplayName("Module service is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ModulesServiceTest {

  @Autowired
  private ModulesService modulesService;

  @Test
  @Sql(scripts = "/sql/data.sql")
  public void add_module() {
    NewModuleDto newModuleDto = new NewModuleDto();
    newModuleDto.setName(ModulesDtoTest.NAME);
    ModuleDto moduleDto = modulesService.addModule(newModuleDto);

    Assertions.assertNotNull(moduleDto);
    Assertions.assertEquals(5, moduleDto.getId());
    Assertions.assertEquals(newModuleDto.getName(), moduleDto.getName());
    Assertions.assertEquals(false, moduleDto.getArchived());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  public void get_modules() {
    List<ModuleDto> modules = modulesService.getModules();

    Assertions.assertNotNull(modules);
    Assertions.assertEquals(4, modules.size());
  }

  @Test
  @Sql(scripts = "/sql/data.sql")
  public void update_module() {
    UpdateModuleDto updateModuleDto = new UpdateModuleDto(ModulesDtoTest.NAME,
        ModulesDtoTest.IS_ARCHIVED);
    ModuleDto moduleDtoAfterUpdate = modulesService.updateModule(ModulesDtoTest.ID,
        updateModuleDto);

    Assertions.assertNotNull(moduleDtoAfterUpdate);
    Assertions.assertEquals(CoursesDtoTest.ID, moduleDtoAfterUpdate.getId());
    Assertions.assertEquals(updateModuleDto.getName(), moduleDtoAfterUpdate.getName());
    Assertions.assertEquals(updateModuleDto.getArchived(), moduleDtoAfterUpdate.getArchived());
  }
}
