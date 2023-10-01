package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Intent {
  public String name;
  public String displayName;
  public TrainingPhrase[] trainingPhrases;
  public DFMessage messages;
}