package de.ait.ems.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * @author Oleksandr Zhurba on 31.10.2023.
 * @project Educational-Management-System-BE
 **/
@OpenAPIDefinition(
    info = @Info(
        title = "Educational management system Api",
        description = "Educational management system", version = "1.0.0",
        contact = @Contact(
            name = "Oleksandr Zhurba, Wladimir Weizen, Tetyana Chernobai, Ekaterina Gorshkova"
        )
    )
)
public class SwaggerConfig {

}
