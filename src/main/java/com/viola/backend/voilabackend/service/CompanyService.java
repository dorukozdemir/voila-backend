package com.viola.backend.voilabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.viola.backend.voilabackend.exceptions.CompanyAlreadyExistException;
import com.viola.backend.voilabackend.model.domain.Admin;
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

 

    public Company createCompany(String name, String email, String phone, String authorityEmail, String authorityName, Admin admin) throws CompanyAlreadyExistException {
        List<Company> companies = companyRepository.findByName(name);
        if (companies != null && !companies.isEmpty())
            throw new CompanyAlreadyExistException();
        Company company = new Company(name,  email,  phone,  authorityEmail,  authorityName, admin);
        return companyRepository.save(company);
    }

    public void save(Company company) {
        companyRepository.save(company);
    }
  
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public boolean isCompanyExist(String name) {
        List<Company> companies = companyRepository.findByName(name);
        if (companies != null && !companies.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
