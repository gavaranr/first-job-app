package com.naveenx.firstjobapp.job.impl;

import com.naveenx.firstjobapp.job.Job;
import com.naveenx.firstjobapp.job.JobService;

import java.util.ArrayList;
import java.util.List;

public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();

    @Override
    public List<Job> findAll() {
        return null;
    }

    @Override
    public void createJob(Job job) {
        jobs.add(job);
    }
}
