package com.paypal.bfs.test.employeeserv.impl.util;

import com.paypal.bfs.test.employeeserv.api.EmployeeException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestValidator {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testPerformValidation() throws EmployeeException {
        EmployeeValidator employeeValidator = new EmployeeValidator();
        Employee employee = new Employee();
        employee.setFirstName("payPal");
        employee.setLastName("Inc");
        employee.setDateOfBirth("2020-01-01");
        employee.setAddressLine1("1 lexington avenue");
        employee.setAddressLine2("");
        employee.setCity("NewYork");
        employee.setState("NewYork");
        employee.setCountry("USA");
        employee.setZipCode("01010");
        Assert.assertTrue(employeeValidator.performValidation(employee));
    }

    @Test
    public void testPerformValidationFirstNameLength() throws EmployeeException {
        EmployeeValidator employeeValidator = new EmployeeValidator();
        Employee employee = new Employee();
        employee.setFirstName("p");
        exception.expect(EmployeeException.class);
        employeeValidator.performValidation(employee);
    }

    @Test
    public void testPerformValidationLastNameLength() throws EmployeeException {
        EmployeeValidator employeeValidator = new EmployeeValidator();
        Employee employee = new Employee();
        employee.setFirstName("payPal");
        employee.setLastName("p");
        exception.expect(EmployeeException.class);
        employeeValidator.performValidation(employee);
    }

    @Test
    public void testPerformValidationCityLength() throws EmployeeException {
        EmployeeValidator employeeValidator = new EmployeeValidator();
        Employee employee = new Employee();
        employee.setFirstName("payPal");
        employee.setLastName("Inc");
        employee.setAddressLine1("1 lexington avenue");
        employee.setCity("N");
        exception.expect(EmployeeException.class);
        employeeValidator.performValidation(employee);
    }
    @Test
    public void testPerformValidationStateLength() throws EmployeeException {
        EmployeeValidator employeeValidator = new EmployeeValidator();
        Employee employee = new Employee();
        employee.setFirstName("payPal");
        employee.setLastName("Inc");
        employee.setAddressLine1("1 lexington avenue");
        employee.setCity("NewYork");
        employee.setState("N");
        exception.expect(EmployeeException.class);
        employeeValidator.performValidation(employee);
    }

    @Test
    public void testPerformValidationCountryLength() throws EmployeeException {
        EmployeeValidator employeeValidator = new EmployeeValidator();
        Employee employee = new Employee();
        employee.setFirstName("payPal");
        employee.setLastName("Inc");
        employee.setAddressLine1("1 lexington avenue");
        employee.setCity("NewYork");
        employee.setState("NewYork");
        employee.setCountry("U");
        exception.expect(EmployeeException.class);
        employeeValidator.performValidation(employee);
    }
    @Test
    public void testPerformValidationZipCodeLength() throws EmployeeException {
        EmployeeValidator employeeValidator = new EmployeeValidator();
        Employee employee = new Employee();
        employee.setFirstName("payPal");
        employee.setLastName("Inc");
        employee.setAddressLine1("1 lexington avenue");
        employee.setCity("NewYork");
        employee.setState("NewYork");
        employee.setCountry("USA");
        employee.setZipCode("");
        exception.expect(EmployeeException.class);
        employeeValidator.performValidation(employee);
    }

    @Test
    public void testPerformValidationDateFormat() throws EmployeeException {
        EmployeeValidator employeeValidator = new EmployeeValidator();
        Employee employee = new Employee();
        employee.setFirstName("payPal");
        employee.setLastName("Inc");
        employee.setAddressLine1("1 lexington avenue");
        employee.setCity("NewYork");
        employee.setState("NewYork");
        employee.setCountry("USA");
        employee.setZipCode("10100");
        employee.setDateOfBirth("22010");
        exception.expect(EmployeeException.class);
        employeeValidator.performValidation(employee);
    }
}
