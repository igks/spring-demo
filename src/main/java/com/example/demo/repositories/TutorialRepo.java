package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Tutorial;

public interface TutorialRepo extends JpaRepository<Tutorial, Long> {

  List<Tutorial> findByPublished(boolean published);

  List<Tutorial> findByTitleContaining(String title);
}