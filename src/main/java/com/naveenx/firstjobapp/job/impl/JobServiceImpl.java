package com.naveenx.firstjobapp.job.impl;

import com.naveenx.firstjobapp.job.Job;
import com.naveenx.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean deleteJobById(Long id) {

        Job job = jobs.stream()
                .filter(eachJob -> eachJob.getId().equals(id))
                .findFirst().orElse(null);

        if (job != null) jobs.remove(job);
        return job != null;
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {

/*
        Use streams when there is extremely large data sets and you need to
        parallelize the data for your use-case

        AtomicBoolean updated = new AtomicBoolean(false);

        jobs.stream()
                .filter(item -> item.getId().equals(id))
                .forEach(item -> {
                    item.setId(id);
                    item.setTitle(updatedJob.getTitle());
                    item.setDescription(updatedJob.getDescription());
                    item.setMinSalary(updatedJob.getMinSalary());
                    item.setMaxSalary(updatedJob.getMaxSalary());
                    item.setLocation(updatedJob.getLocation());
                    updated.set(true);});

        return updated;
* */

        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true;
            }
        }
        return false;
    }
}
