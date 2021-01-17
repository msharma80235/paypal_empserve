package com.paypal.bfs.test.employeeserv.impl.service;

import com.paypal.bfs.test.employeeserv.api.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.api.EmployeeException;
import com.paypal.bfs.test.employeeserv.api.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.api.EmployeeService;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.impl.mapper.EmployeePOJOToEntityMapperImpl;
import com.paypal.bfs.test.employeeserv.impl.util.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.logging.Logger;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = Logger.getLogger(EmployeeValidator.class.getName());

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeePOJOToEntityMapperImpl mapper;

    @Autowired
    EmployeeValidator employeeValidator;


    @Override
    public Employee getById(int id) throws EmployeeException {
        EmployeeEntity entity = employeeRepository.findById(id).get();
        return mapper.mapEntityToPoJo(employeeRepository.findById(id).get());
    }

    @Override
    public Employee save(Employee employee) throws ParseException, EmployeeException {
        EmployeeEntity savedEntity = null;
        if(employeeValidator.performValidation(employee)) {
            EmployeeEntity entity = employeeRepository.findByAllFields(
                    employee.getFirstName(),
                    employee.getLastName(),
                    new java.sql.Date(employeeValidator.parseDate(employee.getDateOfBirth()).getTime()),
                    employee.getAddressLine1(),
                    employee.getCity(),
                    employee.getState(),
                    employee.getCountry(),
                    employee.getZipCode());

            log.info("Entity: " + entity);
            if(entity != null){
                throw new EmployeeException("Employee with First name: " + employee.getFirstName() +
                        " and last name: " + employee.getLastName() +" already exists !!!!");
            }
            savedEntity = employeeRepository.save(mapper.mapPoJoToEntity(employee));
            log.info("Found : " + savedEntity);

        }else{
            log.info(" Refer above log for problems with Employee object !!! ");
        }
        return mapper.mapEntityToPoJo(savedEntity);
    }
}
