package com.naveenx.firstjobapp.job.impl;

import com.naveenx.firstjobapp.job.Job;
import com.naveenx.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();
    private Job job;
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public Job getJobById(Long id) {

        return jobs.stream()
                .filter(eachJob -> eachJob.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }
}
