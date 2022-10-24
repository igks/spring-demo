package com.example.demo.models;

import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tutorial_details")
public class TutorialDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Date createdOn;

  @Column
  private String createdBy;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "tutorial_id")
  private Tutorial tutorial;
}