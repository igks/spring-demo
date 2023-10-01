package com.example.demo.controllers;

import org.springframework.web.client.RestTemplate;

import com.example.demo.constants.DfUrl;
import com.example.demo.models.DFMessage;
import com.example.demo.models.DFText;
import com.example.demo.models.DfAgent;
import com.example.demo.models.Intent;
import com.example.demo.models.Message;
import com.example.demo.models.Part;
import com.example.demo.models.TrainingPhrase;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.*;
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
    String url = DfUrl.get(DfUrl.AGENT);

    RestTemplate API = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + token);
    headers.add("x-goog-user-project", "agent1-avpp");

    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> result = API.exchange(
        url, HttpMethod.GET, requestEntity, String.class);
    return result;
  }

  @PostMapping("/create-agent")
  public ResponseEntity<String> createAgent() throws IOException {
    String token = getAccessToken();
    String url = DfUrl.get(DfUrl.AGENT);

    RestTemplate API = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + token);
    headers.add("x-goog-user-project", "agent1-avpp");

    DfAgent agent = new DfAgent();
    agent.displayName = "New_Agent_Spring";
    agent.defaultLanguageCode = "en";
    agent.timeZone = "Asia/Bangkok";

    HttpEntity<DfAgent> requestEntity = new HttpEntity<DfAgent>(agent, headers);

    ResponseEntity<String> result = API.exchange(url, HttpMethod.POST, requestEntity, String.class);

    return result;
  }

  @PostMapping("/create-intent")
  public ResponseEntity<String> createIntent() throws IOException {
    String token = getAccessToken();
    String url = DfUrl.get(DfUrl.INTENTS);

    RestTemplate API = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + token);
    headers.add("x-goog-user-project", "agent1-avpp");

    UUID uuid = UUID.randomUUID();
    TrainingPhrase[] trainingPhrases = new TrainingPhrase[] {
        new TrainingPhrase(
            uuid.toString(),
            "EXAMPLE",
            new Part[] {
                new Part("Text1"),
                new Part("Text2"),
                new Part("Text3") })
    };

    Intent intent = new Intent();
    intent.displayName = "New_Intest_Spring";
    intent.name = "";
    intent.trainingPhrases = trainingPhrases;

    DFText texts = new DFText(new String[] { "Sampel message", "Sample message 2", "Sample message 3" });
    intent.messages = new DFMessage(texts);

    HttpEntity<Intent> requestEntity = new HttpEntity<Intent>(intent, headers);

    ResponseEntity<String> result = API.exchange(url, HttpMethod.POST, requestEntity, String.class);

    return result;
  }
}