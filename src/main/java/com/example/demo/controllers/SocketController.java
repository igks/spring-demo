package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Message;

@RestController
@RequestMapping("/socket")
public class SocketController {
  @Autowired
  SimpMessagingTemplate template;

  @PostMapping("/send")
  public ResponseEntity<Void> sendMessage(@RequestBody Message message) {
    template.convertAndSend("/topic/message", message.getMessage());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @MessageMapping("/sendMessage")
  public void receiveMessage(@Payload Message message) {
    // receive message from client
  }

  @SendTo("/topic/message")
  public Message broadcastMessage(@Payload Message message) {
    return message;
  }

  @MessageMapping("/user")
  @SendTo("/topic")
  public Message send(@Payload Message message) {
    return message;
  }
}

/*
 * sendMessage handles POST request sent to server. It uses
 * SimpMessagingTemplate to pass message to “/topic/message” destination.
 * 
 * receiveMessage is called whenever message is sent from client to
 * “/app/sendMessage”. Here, “/app” prefix is from WebSocket Configuration.
 * Please make note of annotations; MessageMapping and Payload.
 * 
 * broadcastMessage method just return payload received from “/send” POST
 * request. Returned value is received by clients register at “/topic/message”.
 */