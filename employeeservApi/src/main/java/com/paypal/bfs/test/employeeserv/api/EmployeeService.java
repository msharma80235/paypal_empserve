package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

import java.text.ParseException;

public interface EmployeeService {
    public Employee getById(int id) throws EmployeeException;
    public Employee save(Employee employee) throws ParseException, EmployeeException;

    }
