package com.viola.backend.voilabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.viola.backend.voilabackend.model.domain.Url;
import com.viola.backend.voilabackend.repository.UrlRepository;

@Service("urlService")
public class UrlService {
    @Autowired
	private UrlRepository urlRepository;

    /* 
    public Company createCompany(String name) throws CompanyAlreadyExistException {
        List<Company> companies = companyRepository.findByName(name);
        if (companies != null && !companies.isEmpty())
            throw new CompanyAlreadyExistException();
        Company company = new Company(name);
        return companyRepository.save(company);
    }
*/
    public void save(Url url) {
        urlRepository.save(url);
    }
  
    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }
}
