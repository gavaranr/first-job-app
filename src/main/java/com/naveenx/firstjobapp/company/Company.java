package com.naveenx.firstjobapp.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.naveenx.firstjobapp.job.Job;
import com.naveenx.firstjobapp.reviews.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Review> reviews;

}
