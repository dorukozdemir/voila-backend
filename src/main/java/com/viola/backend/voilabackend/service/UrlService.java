package com.viola.backend.voilabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public boolean isUrlExist(String token) {
        List<Url> urls = urlRepository.findByToken(token);
        if(urls != null && !urls.isEmpty()) {
            return true;
        } 
        return false;
    }

    public Page<Url> getAllUrls(int start, int size) {
        Pageable pagination = PageRequest.of(start, size, Sort.by("created").descending());
        return urlRepository.findAll(pagination);
    }

    public Url getUrlByToken(String token) {
        List<Url> urls = urlRepository.findByToken(token);
        if(urls != null && !urls.isEmpty()) {
            return urls.get(0);
        }
        return null;
    }
}
