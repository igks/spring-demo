package com.example.demo.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.TutorialDetails;

@Repository
public interface TutorialDetailsRepo extends JpaRepository<TutorialDetails, Long> {
  @Transactional
  void deleteById(long id);

  @Transactional
  void deleteByTutorialId(long tutorialId);
}