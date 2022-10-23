package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Tutorial;
import com.example.demo.models.TutorialDetails;
import com.example.demo.repositories.TutorialDetailsRepo;
import com.example.demo.repositories.TutorialRepo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TutorialDetailsController {
  @Autowired
  private TutorialDetailsRepo detailsRepo;

  @Autowired
  private TutorialRepo tutorialRepo;

  @GetMapping({ "/details/{id}", "/tutorials/{id}/details" })
  public ResponseEntity<TutorialDetails> getDetailsById(@PathVariable(value = "id") Long id) {
    TutorialDetails details = detailsRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial Details with id = " + id));

    return new ResponseEntity<>(details, HttpStatus.OK);
  }

  @PostMapping("/tutorials/{tutorialId}/details")
  public ResponseEntity<TutorialDetails> createDetails(@PathVariable(value = "tutorialId") Long tutorialId,
      @RequestBody TutorialDetails detailsRequest) {
    Tutorial tutorial = tutorialRepo.findById(tutorialId)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

    detailsRequest.setCreatedOn(new java.util.Date());
    detailsRequest.setTutorial(tutorial);
    TutorialDetails details = detailsRepo.save(detailsRequest);

    return new ResponseEntity<>(details, HttpStatus.CREATED);
  }

  @PutMapping("/details/{id}")
  public ResponseEntity<TutorialDetails> updateDetails(@PathVariable("id") long id,
      @RequestBody TutorialDetails detailsRequest) {
    TutorialDetails details = detailsRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " not found"));

    details.setCreatedBy(detailsRequest.getCreatedBy());

    return new ResponseEntity<>(detailsRepo.save(details), HttpStatus.OK);
  }

  @DeleteMapping("/details/{id}")
  public ResponseEntity<HttpStatus> deleteDetails(@PathVariable("id") long id) {
    detailsRepo.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/tutorials/{tutorialId}/details")
  public ResponseEntity<TutorialDetails> deleteDetailsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
    if (!tutorialRepo.existsById(tutorialId)) {
      throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
    }

    detailsRepo.deleteByTutorialId(tutorialId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}