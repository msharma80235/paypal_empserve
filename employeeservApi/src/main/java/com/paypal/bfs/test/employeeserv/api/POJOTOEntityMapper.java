package com.paypal.bfs.test.employeeserv.api;

import java.text.ParseException;

public interface POJOTOEntityMapper<E, P> {

    public P mapEntityToPoJo(E entity) throws EmployeeException;
    public E mapPoJoToEntity(P poJo) throws ParseException, EmployeeException;
}

