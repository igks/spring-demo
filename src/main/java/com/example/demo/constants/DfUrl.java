package com.example.demo.constants;

public class DfUrl {
  public static final String AGENT = "/agent";
  public static final String INTENTS = "/agent/intents";

  public static String get(String uri) {
    String Domain = "https://dialogflow.googleapis.com/v2/projects/";
    String ProjectId = "agent1-avpp/";

    return Domain + ProjectId + uri;
  }
}
