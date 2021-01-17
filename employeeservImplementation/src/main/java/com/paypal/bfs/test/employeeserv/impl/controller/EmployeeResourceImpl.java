package com.paypal.bfs.test.employeeserv.impl.controller;

import com.paypal.bfs.test.employeeserv.api.EmployeeException;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.EmployeeService;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.text.ParseException;

/**
 * Implementation class for employee resource.
 */
@RestController
@Transactional
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @Override
    @GetMapping(path = "/v1/bfs/employees/get/{id}")
    public ResponseEntity<Employee> employeeGetById(String id) throws EmployeeException {
        Employee employee = employeeService.getById(Integer.parseInt(id));
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/v1/bfs/employees/create")
    public ResponseEntity<Employee> createEmployee(Employee employee) throws ParseException, EmployeeException {
        Employee emp = employeeService.save(employee);
        return new ResponseEntity<Employee>(emp, HttpStatus.OK);
    }

}
