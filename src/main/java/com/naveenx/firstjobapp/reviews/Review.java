package com.naveenx.firstjobapp.reviews;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.naveenx.firstjobapp.company.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String review;
    private String rating;

    @JsonIgnore
    @ManyToOne // Indicates a many-to-one relationship with Company
    private Company company;
}

