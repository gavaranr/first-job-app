package com.naveenx.firstjobapp.company.Impl;

import com.naveenx.firstjobapp.company.Company;
import com.naveenx.firstjobapp.company.CompanyRepository;
import com.naveenx.firstjobapp.company.CompanyService;
import com.naveenx.firstjobapp.reviews.Review;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyServiceImpl (CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long companyId) {

        return companyRepository.findById(companyId).orElse(null);
    }

    @Override
    public boolean updateCompany(Long companyId, Company updatedCompany) {

        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(updatedCompany.getDescription());
            companyToUpdate.setName(updatedCompany.getName());
            companyToUpdate.setJobs(updatedCompany.getJobs());
            companyRepository.save(companyToUpdate);
            return true;
        } else return false;
    }

    @Override
    public boolean deleteCompanyById(Long companyId) {

        if (companyRepository.existsById(companyId)) {
            companyRepository.deleteById(companyId);
            return true;
        } else  {
            return false;
        }
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }
}
