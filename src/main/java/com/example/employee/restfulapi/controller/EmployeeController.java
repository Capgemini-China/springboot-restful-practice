package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = "")
    public ResponseEntity<List<Employee>> getAllEmployees() throws Exception {
        List<Employee> employeeList = employeeRepository.findAll();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> getCompanyById(@PathVariable Long id) throws Exception {
        Employee employee = employeeRepository.getById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
