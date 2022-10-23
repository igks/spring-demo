package com.example.demo.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {
  List<Comment> findByTutorialId(Long postId);

  @Transactional
  void deleteByTutorialId(long tutorialId);
}