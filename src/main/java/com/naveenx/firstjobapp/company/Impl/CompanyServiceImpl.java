package com.naveenx.firstjobapp.company.Impl;

import com.naveenx.firstjobapp.company.Company;
import com.naveenx.firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private List<Company> companies = new ArrayList<>();
    private Company company;
    private Long nextId = 1L;

    @Override
    public List<Company> findAll() {
        return companies;
    }

    @Override
    public Company getCompanyById(Long id) {

        return companies.stream()
                .filter(eachCompany -> eachCompany.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateCompany(Long id, Company updatedCompany) {

        for (Company company : companies) {
            if (company.getId().equals(id)) {
                company.setLocation(updatedCompany.getLocation());
                company.setDescription(updatedCompany.getDescription());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteCompanyById(Long id) {

        Company company = companies.stream()
                .filter(eachCompany -> eachCompany.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (company != null) companies.remove(company);
        return company != null;
    }

    @Override
    public void createCompany(Company company) {
        company.setId(nextId++);
        companies.add(company);
    }
}
