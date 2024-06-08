package com.naveenx.firstjobapp.company;

import com.naveenx.firstjobapp.reviews.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class Company {

    private Long id;
    private String description;
    private String location;

    public List<Review> getReviews(Long id) {
        return new ArrayList<Review>();
    }
}
