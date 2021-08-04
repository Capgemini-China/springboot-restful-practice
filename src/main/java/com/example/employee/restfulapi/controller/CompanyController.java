package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.*;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/companies")
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping(value = "")
    public ResponseEntity<List<Company>> getCompanies() throws Exception {
        List<Company> companyList = companyRepository.findAll();
        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) throws Exception {
        Company company = companyRepository.getById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
}
