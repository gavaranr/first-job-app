package com.naveenx.firstjobapp.company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    Company getCompanyById(Long id);

    boolean updateCompany(Long id, Company company);

    boolean deleteCompanyById(Long id);

    void createCompany(Company company);
}
