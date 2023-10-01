package com.example.demo.controllers;

import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.iservices.JobService;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping("/job")
public class JobController {

  @Autowired
  private JobScheduler jobScheduler;

  @Autowired
  private JobService jobService;

  @GetMapping("/run")
  public String runJob(
      @RequestParam(value = "name", defaultValue = "Hello World") String name) {

    jobScheduler.enqueue(() -> jobService.execute(name));
    return "Job is enqueued.";

  }

  @GetMapping("/schedule")
  public String scheduleJob(
      @RequestParam(value = "name", defaultValue = "Hello World") String name,
      @RequestParam(value = "when", defaultValue = "PT3H") String when) {

    jobScheduler.schedule(
        Instant.now().plus(Duration.parse(when)),
        () -> jobService.execute(name));

    return "Job is scheduled.";
  }

}