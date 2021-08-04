package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = "")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> getCompanyById(@PathVariable Long id) {
        Employee employee = employeeRepository.getById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping(value = "/page/{page}/pageSize/{pageSize}")
    public ResponseEntity<Page<Employee>> getEmployeeByPage(@PathVariable int page, @PathVariable int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(value = "/male")
    public ResponseEntity<List<Employee>> getEmployeeByGender() {
        List<Employee> employeeList = employeeRepository.findByGender("male");
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<HttpStatus> addCompany(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployee) {
        Employee employee = employeeRepository.getById(id);

        employee.setName(newEmployee.getName());
        employee.setAge(newEmployee.getAge());
        employee.setGender(newEmployee.getGender());
        employee.setSalary(newEmployee.getSalary());
        employee.setCompanyId(newEmployee.getCompanyId());

        employeeRepository.save(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
