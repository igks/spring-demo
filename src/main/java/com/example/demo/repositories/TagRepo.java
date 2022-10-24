package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Tag;

public interface TagRepo extends JpaRepository<Tag, Long> {
  List<Tag> findTagsByTutorialsId(Long tutorialId);
}