package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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


    @GetMapping(value = "/{id}/employees")
    public ResponseEntity<Set<Employee>> getEmployeeByCompanyId(@PathVariable Long id) throws Exception {
        Company company = companyRepository.getById(id);
        Set<Employee> employees = company.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(value = "/page/{page}/pageSize/{pageSize}")
    public ResponseEntity<Page<Company>> getCompanyByPage(@PathVariable int page, @PathVariable int pageSize) throws Exception {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        Page<Company> companies = companyRepository.findAll(pageable);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping(value = "/companies")
    public ResponseEntity<HttpStatus> addCompany(@RequestBody Company company) throws Exception {
        companyRepository.save(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
