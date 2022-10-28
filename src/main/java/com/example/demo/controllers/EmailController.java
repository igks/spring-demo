package com.example.demo.controllers;

// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Email;
import com.example.demo.services.EmailService;

// Annotation
@RestController
// Class
@RequestMapping("/email")
public class EmailController {

  @Autowired
  private EmailService emailService;

  // Sending a simple Email
  @PostMapping("/sendMail")
  public String sendMail(@RequestBody Email details) {
    String status = emailService.sendSimpleMail(details);

    return status;
  }

  // Sending email with attachment
  @PostMapping("/sendMailWithAttachment")
  public String sendMailWithAttachment(
      @RequestBody Email details) {
    String status = emailService.sendMailWithAttachment(details);

    return status;
  }
}