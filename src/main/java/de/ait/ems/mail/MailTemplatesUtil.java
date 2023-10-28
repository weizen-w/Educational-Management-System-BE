package de.ait.ems.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 26/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Component
@RequiredArgsConstructor
public class MailTemplatesUtil {

  private final Configuration freemarkerConfiguration;

  public String createConfirmationMail(String firstName, String lastName, String link) {
    try {
      Template template = freemarkerConfiguration.getTemplate("confirm_registration_mail.ftlh");
      Map<String, Object> model = new HashMap<>();
      model.put("firstName", firstName);
      model.put("lastName", lastName);
      model.put("link", link);
      return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }
}
