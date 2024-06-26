package com.naveenx.firstjobapp.job;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.naveenx.firstjobapp.company.Company;
import com.naveenx.firstjobapp.company.CompanyRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
//@Table(name = "Jobs")
@Getter
@Setter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    @JsonIgnore
    @ManyToOne
    private Company company;
}
