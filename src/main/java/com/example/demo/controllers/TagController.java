package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Tag;
import com.example.demo.models.Tutorial;
import com.example.demo.repositories.TagRepo;
import com.example.demo.repositories.TutorialRepo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TagController {

  @Autowired
  private TutorialRepo tutorialRepo;

  @Autowired
  private TagRepo tagRepo;

  @GetMapping("/tags")
  public ResponseEntity<List<Tag>> getAllTags() {
    List<Tag> tags = new ArrayList<Tag>();

    tagRepo.findAll().forEach(tags::add);

    if (tags.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(tags, HttpStatus.OK);
  }

  @GetMapping("/tutorials/{tutorialId}/tags")
  public ResponseEntity<List<Tag>> getAllTagsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
    if (!tutorialRepo.existsById(tutorialId)) {
      throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
    }

    List<Tag> tags = tagRepo.findTagsByTutorialsId(tutorialId);
    return new ResponseEntity<>(tags, HttpStatus.OK);
  }

  @GetMapping("/tags/{id}")
  public ResponseEntity<Tag> getTagsById(@PathVariable(value = "id") Long id) {
    Tag tag = tagRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + id));

    return new ResponseEntity<>(tag, HttpStatus.OK);
  }

  @GetMapping("/tags/{tagId}/tutorials")
  public ResponseEntity<List<Tutorial>> getAllTutorialsByTagId(@PathVariable(value = "tagId") Long tagId) {
    if (!tagRepo.existsById(tagId)) {
      throw new ResourceNotFoundException("Not found Tag  with id = " + tagId);
    }

    List<Tutorial> tutorials = tutorialRepo.findTutorialsByTagsId(tagId);
    return new ResponseEntity<>(tutorials, HttpStatus.OK);
  }

  @PostMapping("/tutorials/{tutorialId}/tags")
  public ResponseEntity<Tag> addTag(@PathVariable(value = "tutorialId") Long tutorialId, @RequestBody Tag tagRequest) {
    Tag tag = tutorialRepo.findById(tutorialId).map(tutorial -> {
      long tagId = tagRequest.getId();

      // tag is existed
      if (tagId != 0L) {
        Tag _tag = tagRepo.findById(tagId)
            .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + tagId));
        tutorial.addTag(_tag);
        tutorialRepo.save(tutorial);
        return _tag;
      }

      // add and create new Tag
      tutorial.addTag(tagRequest);
      return tagRepo.save(tagRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

    return new ResponseEntity<>(tag, HttpStatus.CREATED);
  }

  @PutMapping("/tags/{id}")
  public ResponseEntity<Tag> updateTag(@PathVariable("id") long id, @RequestBody Tag tagRequest) {
    Tag tag = tagRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("TagId " + id + "not found"));

    tag.setName(tagRequest.getName());

    return new ResponseEntity<>(tagRepo.save(tag), HttpStatus.OK);
  }

  @DeleteMapping("/tutorials/{tutorialId}/tags/{tagId}")
  public ResponseEntity<HttpStatus> deleteTagFromTutorial(@PathVariable(value = "tutorialId") Long tutorialId,
      @PathVariable(value = "tagId") Long tagId) {
    Tutorial tutorial = tutorialRepo.findById(tutorialId)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

    tutorial.removeTag(tagId);
    tutorialRepo.save(tutorial);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/tags/{id}")
  public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") long id) {
    tagRepo.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}