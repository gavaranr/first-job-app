package com.naveenx.firstjobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {

        List<Company> companyList = new ArrayList<>(companyService.getAllCompanies());

        if (!companyList.isEmpty()) {
            return new ResponseEntity<>(companyList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(companyList, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {

        Company company = companyService.getCompanyById(id);

        return (company != null) ?
                new ResponseEntity<>(company, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {

        boolean updated = companyService.updateCompany(id, company);

        return updated ? new ResponseEntity<>(
                "Company updated successfully", HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {

        boolean isDeleted = companyService.deleteCompanyById(id);

        return isDeleted ? new ResponseEntity<>(
                "Company deleted successfully", HttpStatus.OK) :
                new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {

        companyService.createCompany(company);

        return new ResponseEntity<>(
                "Company created successfully", HttpStatus.CREATED);
    }
}
