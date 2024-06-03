package com.naveenx.firstjobapp.job;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public List<Job> findAll () {
        return jobService.findAll();
    }

    @GetMapping("/jobs/{id}")
    public Job getJobById (@PathVariable Long id) {

        Job job = jobService.getJobById(id);

        return (job!=null) ?
                job : new Job (1L, "TestJob", "TestJob",
        "2000", "3000", "TestLocation");
    }

    @PostMapping("/jobs")
    public String createJob (@RequestBody Job job) {
        jobService.createJob(job);
        return "Job added successfully";
    }
}

/*

GET /jobs: Get all jobs
GET /jobs/{id}: Get a specific job by ID
POST /jobs: Create a new job (request body should contain the job details)
DELETE /jobs/{id}: Delete a specific job by ID
PUT /jobs/{id}: Update a specific job by ID (request body should contain the updated jol
GET /jobs/{id}/company: Get the company associated with a specific job by ID

Example API URLs:
GET {base_url}/jobs
GET {base_url}/jobs/1
POST {base_url}/jobs
DELETE {base_url}/jobs/1
PUT {base_url}/jobs/1

*/