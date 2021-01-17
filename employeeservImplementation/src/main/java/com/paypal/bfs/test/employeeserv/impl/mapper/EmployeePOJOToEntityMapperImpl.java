package com.paypal.bfs.test.employeeserv.impl.mapper;

import com.paypal.bfs.test.employeeserv.api.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.api.EmployeeException;
import com.paypal.bfs.test.employeeserv.api.POJOTOEntityMapper;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.impl.util.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class EmployeePOJOToEntityMapperImpl implements POJOTOEntityMapper<EmployeeEntity, Employee> {

    @Autowired
    EmployeeValidator employeeValidator;

    @Override
    public Employee mapEntityToPoJo(EmployeeEntity entity) throws EmployeeException {
        Employee emp = new Employee();
        emp.setId(entity.getId());
        emp.setFirstName(entity.getFirstName());
        emp.setLastName(entity.getLastName());
        emp.setDateOfBirth(employeeValidator.formatDate(entity.getDateOfBirth()));
        emp.setAddressLine1(entity.getAddressLine1());
        emp.setAddressLine2(entity.getAddressLine2());
        emp.setCity(entity.getCity());
        emp.setState(entity.getState());
        emp.setCountry(entity.getCountry());
        emp.setZipCode(entity.getZipCode());
        return emp;
    }

    @Override
    public EmployeeEntity mapPoJoToEntity(Employee poJo) throws ParseException, EmployeeException {
        EmployeeEntity empEntity = new EmployeeEntity();
        empEntity.setId(poJo.getId());
        empEntity.setFirstName(poJo.getFirstName());
        empEntity.setLastName(poJo.getLastName());
        empEntity.setDateOfBirth(employeeValidator.parseSQLDate(poJo.getDateOfBirth()));
        empEntity.setAddressLine1(poJo.getAddressLine1());
        empEntity.setAddressLine2(poJo.getAddressLine2());
        empEntity.setCity(poJo.getCity());
        empEntity.setState(poJo.getState());
        empEntity.setCountry(poJo.getCountry());
        empEntity.setZipCode(String.valueOf(poJo.getZipCode()));
        return empEntity;
    }
}
