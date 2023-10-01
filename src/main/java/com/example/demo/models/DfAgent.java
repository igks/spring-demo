package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

public class DfAgent {
  public String displayName;
  public String defaultLanguageCode;
  public String timeZone;
}
