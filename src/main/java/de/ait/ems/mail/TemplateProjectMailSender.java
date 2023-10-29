package de.ait.ems.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 26/10/2023 EducationalManagementSystem
 *
 * @author Wladimir Weizen
 */
@Component
@RequiredArgsConstructor
public class TemplateProjectMailSender {

  private final JavaMailSender javaMailSender;

  @Async
  public void send(String email, String subject, String text) {
    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
    try {
      helper.setTo(email);
      helper.setSubject(subject);
      helper.setText(text, true);
    } catch (MessagingException e) {
      throw new IllegalStateException(e);
    }
    javaMailSender.send(message);
  }
}
