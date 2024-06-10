package com.naveenx.firstjobapp.reviews;

import com.naveenx.firstjobapp.company.Company;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Review {
    @Id
    private Long id;
    private String review;

    @ManyToOne // Indicates a many-to-one relationship with Company
    private Company company;

    public void setCompany(Company company) {
        this.company = company;
    }
}

