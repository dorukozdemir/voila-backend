package com.viola.backend.voilabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.viola.backend.voilabackend.exceptions.CompanyAlreadyExistException;
import com.viola.backend.voilabackend.model.domain.Company;
import com.viola.backend.voilabackend.repository.CompanyRepository;

@Service("companyService")
public class CompanyService {
    @Autowired
	private CompanyRepository companyRepository;

    public Company getCompanyById(String id) {
        Company company = companyRepository.findById(id);
		return company;
	}

 

    public Company createCompany(String name) throws CompanyAlreadyExistException {
        List<Company> companies = companyRepository.findByName(name);
        if (companies != null && !companies.isEmpty())
            throw new CompanyAlreadyExistException();
        Company company = new Company(name);
        return companyRepository.save(company);
    }

    public void save(Company company) {
        companyRepository.save(company);
    }
  
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
