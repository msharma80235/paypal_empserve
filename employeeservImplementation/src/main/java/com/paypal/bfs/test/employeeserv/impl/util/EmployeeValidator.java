package com.paypal.bfs.test.employeeserv.impl.util;

import com.paypal.bfs.test.employeeserv.api.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.api.EmployeeException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.impl.mapper.EmployeePOJOToEntityMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
@Component
public class EmployeeValidator {

    private static final Logger log = Logger.getLogger(EmployeeValidator.class.getName());
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


    @Autowired
    EmployeePOJOToEntityMapperImpl mapper;

    public boolean performValidation(Employee emp) throws EmployeeException {

        if(!validLength(emp.getFirstName(), "First Name")){
            throw new EmployeeException("First name is not of required length !!!");
        }else if(!validLength(emp.getLastName(), "Last Name")){
            throw new EmployeeException("Last name is not of required length !!!");
        }else if(!validLength(emp.getAddressLine1(), "Address line 1")){
            throw new EmployeeException("Address Line 1 is not of required length !!!");
        }else if(!validLength(emp.getCity(), "City")){
            throw new EmployeeException("Address city is not of required length !!!");
        }else if(!validLength(emp.getState(), "State")){
            throw new EmployeeException("Address state is not of required length !!!");
        }else if(!validLength(emp.getCountry(), "Country")){
            throw new EmployeeException("Address Country is not of required length !!!");
        }else if((emp.getZipCode().length() < 5 || emp.getZipCode().length() > 10)){
            throw new EmployeeException("Address zipcode should be 5 or 10 digit long !!!");
        }else if(!validDate(emp.getDateOfBirth())){
        }
        return true;
    }

    private boolean validLength(String value, String type) {
        if(value == null ){
            log.info("Value of " + type + " can not be null !!!");
            return false;
        }
        if (value.length() < 2 || value.length() > 255) {
            log.info("Length  of " + type + "   should be 1 to 255 characters long !!!");
            return false;
        }
        return true;
    }
    private boolean validDate(String date) throws EmployeeException {
        try {
            Date d = formatter.parse(date);
        } catch (Exception e) {
            throw new EmployeeException("Date is not formatted, required : yyyy-MM-dd!!!");
        }
        return true;
    }

    public Date parseDate(String date) throws EmployeeException{
        Date d = null;
        try {
            d = formatter.parse(date);
        } catch (Exception e) {
            throw new EmployeeException("Date is not formatted, required : yyyy-MM-dd!!!");
        }
        return d;
    }

    public java.sql.Date parseSQLDate(String date) throws EmployeeException{
        Date d = null;
        try {
            d = formatter.parse(date);
        } catch (Exception e) {
            throw new EmployeeException("Date is not formatted, required : yyyy-MM-dd!!!");
        }
        return new java.sql.Date(d.getTime());
    }

    public String formatDate(Date date) throws EmployeeException{
        if (date == null ){
            throw new EmployeeException("Date can not be null!!!");
        }
        return formatter.format(date);
    }



    public boolean verifyDuplicates(Employee emp, List<EmployeeEntity> list) throws EmployeeException {
        for(EmployeeEntity e: list){
            if(emp.equals(mapper.mapEntityToPoJo(e))){
                return false;
            }
        }
        return true;
    }
}
