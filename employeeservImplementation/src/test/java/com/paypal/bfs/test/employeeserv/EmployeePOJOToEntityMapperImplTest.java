package com.paypal.bfs.test.employeeserv;

import com.paypal.bfs.test.employeeserv.api.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.api.EmployeeException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.impl.mapper.EmployeePOJOToEntityMapperImpl;
import com.paypal.bfs.test.employeeserv.impl.util.EmployeeValidator;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmployeeValidator.class, EmployeePOJOToEntityMapperImpl.class})
public class EmployeePOJOToEntityMapperImplTest {


    @Autowired EmployeeValidator employeeValidator;
    @Autowired EmployeePOJOToEntityMapperImpl eMapper;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testMapEntityToPoJo() throws ParseException, EmployeeException {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName("payPal");
        employeeEntity.setLastName("Inc");
        employeeEntity.setDateOfBirth(Date.valueOf(employeeValidator.formatDate(new Date(2020-02-02))));
        employeeEntity.setAddressLine1("1 avenue");
        employeeEntity.setAddressLine2("");
        employeeEntity.setCity("NewYork");
        employeeEntity.setState("NewYork");
        employeeEntity.setCountry("USA");
        employeeEntity.setZipCode("08854");

        Employee employee = new Employee();
        employee.setFirstName("payPal");
        employee.setLastName("Inc");
        employee.setDateOfBirth("2020-01-01");
        employee.setAddressLine1("1 avenue");
        employee.setAddressLine2("");
        employee.setCity("NewYork");
        employee.setState("NewYork");
        employee.setCountry("USA");
        employee.setZipCode("08854");
        Assert.assertNotNull(eMapper.mapEntityToPoJo(employeeEntity));
    }

    @Test
    public void testMapPoJoToEntity() throws ParseException, EmployeeException {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("payPal");
        employee.setLastName("Inc");
        employee.setDateOfBirth("2020-01-01");
        employee.setAddressLine1("1 avenue");
        employee.setAddressLine2("");
        employee.setCity("NewYork");
        employee.setState("NewYork");
        employee.setCountry("USA");
        employee.setZipCode("08854");

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1);
        employeeEntity.setFirstName("payPal");
        employeeEntity.setLastName("Inc");
        employeeEntity.setDateOfBirth(employeeValidator.parseSQLDate("2020-01-01"));
        employeeEntity.setAddressLine1("1 avenue");
        employeeEntity.setAddressLine2("");
        employeeEntity.setCity("NewYork");
        employeeEntity.setState("NewYork");
        employeeEntity.setCountry("USA");
        employeeEntity.setZipCode("08854");
        Assert.assertNotNull(eMapper.mapPoJoToEntity(employee));
    }
}
