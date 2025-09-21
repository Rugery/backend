package com.training.backend.Services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
  private final JavaMailSender mailSender;

  // Method to send email notification when a user earns a new insignia
  public void sendInsigniaNotification(String to, String insigniaName) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject("¡Nueva Insignia Obtenida!");
    message.setText("¡Felicidades! Has obtenido la insignia \"" + insigniaName + "\".");
    mailSender.send(message);
  }
}
