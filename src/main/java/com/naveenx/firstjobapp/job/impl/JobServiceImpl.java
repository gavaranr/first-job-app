package com.naveenx.firstjobapp.job.impl;

import com.naveenx.firstjobapp.job.Job;
import com.naveenx.firstjobapp.job.JobRepository;
import com.naveenx.firstjobapp.job.JobService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;
    public JobServiceImpl (JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    private Job job;
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long id) {

        jobRepository.findById(id).orElse(null);
    }
    @Override
    public void createJob(@NotNull Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public boolean deleteJobById(Long id) {

        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {

        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            return true;
        }
        return false;
    }
}
