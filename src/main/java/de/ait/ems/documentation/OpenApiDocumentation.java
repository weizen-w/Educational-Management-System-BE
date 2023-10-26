package de.ait.ems.documentation;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import java.util.Collections;

/**
 * 26/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
public class OpenApiDocumentation {

  public static Paths buildAuthenticationPath() {
    return new Paths()
        .addPathItem("/api/login", buildLoginPathItem())
        .addPathItem("/api/logout", buildLogoutPathItem());
  }

  public static PathItem buildLogoutPathItem() {
    return new PathItem().post(
        new Operation()
            .addTagsItem("Authentication")
            .responses(new ApiResponses()
                .addApiResponse("200", new ApiResponse().description("Successful exit"))));
  }

  public static PathItem buildLoginPathItem() {
    return new PathItem().post(
        new Operation()
            .addTagsItem("Authentication")
            .requestBody(buildLoginRequestBody())
            .description("Login to the application using username and password")
            .responses(new ApiResponses()
                .addApiResponse("200",
                    new ApiResponse()
                        .description("Successful authentication")
                        .content(new Content().addMediaType("application/json",
                            new MediaType().schema(new Schema<>().$ref("StandardResponseDto"))))
                        .headers(
                            Collections
                                .singletonMap("Set-Cookie",
                                    new Header()
                                        .example("JSESSIONID=1234")
                                        .description("Session ID"))))
                .addApiResponse("401",
                    new ApiResponse()
                        .description("Wrong login or password")
                        .content(new Content()
                            .addMediaType("application/json",
                                new MediaType()
                                    .schema(new Schema<>().$ref("StandardResponseDto")))))));
  }

  public static RequestBody buildLoginRequestBody() {
    return new RequestBody().content(
        new Content()
            .addMediaType("application/x-www-form-urlencoded",
                new MediaType()
                    .schema(new Schema<>()
                        .$ref("EmailAndPassword"))));
  }

  public static SecurityRequirement buildSecurity() {
    return new SecurityRequirement().addList("CookieAuthentication");
  }

  public static Schema<?> emailAndPassword() {
    return new Schema<>()
        .type("object")
        .description("User email and password")
        .addProperty("username", new Schema<>().type("string"))
        .addProperty("password", new Schema<>().type("string"));
  }

  public static SecurityScheme securityScheme() {
    return new SecurityScheme()
        .name("cookieAuth")
        .type(SecurityScheme.Type.APIKEY)
        .in(SecurityScheme.In.COOKIE)
        .name("JSESSIONID");
  }
}
