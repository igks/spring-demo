package com.example.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Date;

import org.springframework.http.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class AgentController {

  private String getAccessToken() throws IOException {
    return GoogleCredentials
        .fromStream(new FileInputStream("dfagent1cred.json"))
        .createScoped("https://www.googleapis.com/auth/cloud-platform")
        .refreshAccessToken()
        .getTokenValue();
  }

  @GetMapping("/get-agent")
  public ResponseEntity<String> getAgentList() throws IOException {
    String token = getAccessToken();

    String url = "https://dialogflow.googleapis.com/v2/projects/agent1-avpp/agent";

    RestTemplate API = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + token);
    headers.add("x-goog-user-project", "agent1-avpp");

    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> result = API.exchange(
        url, HttpMethod.GET, requestEntity, String.class);
    return result;
  }
}