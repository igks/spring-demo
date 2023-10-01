package com.example.demo.iservices;

import com.example.demo.models.Email;

public interface EmailService {

  // Method
  // To send a simple email
  String sendSimpleMail(Email details);

  // Method
  // To send an email with attachment
  String sendMailWithAttachment(Email details);
}