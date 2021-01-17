package com.paypal.bfs.test.employeeserv;

import com.paypal.bfs.test.employeeserv.api.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.api.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EmployeeservApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class EmployeeAPIIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void whenPostCreateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(0);
        employee.setFirstName("payPal");
        employee.setLastName("Inc");
        employee.setDateOfBirth("2020-01-01");
        employee.setAddressLine1("1 avenue");
        employee.setAddressLine2("");
        employee.setCity("NewYork");
        employee.setState("NewYork");
        employee.setCountry("USA");
        employee.setZipCode("08854");
        mvc.perform(post("/v1/bfs/employees/create").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(employee)));

        List<EmployeeEntity> found = employeeRepository.findAll();
        assertThat(found).extracting(EmployeeEntity::getFirstName).containsOnly("payPal");
    }

    @Test(expected = Exception.class)
    public void whenPostCreateEmployeeWithInvalidDate() throws Exception {
        Employee employee = new Employee();
        employee.setId(0);
        employee.setFirstName("payPal");
        employee.setLastName("Inc");
        employee.setDateOfBirth("2020-01-xxx");
        employee.setAddressLine1("1 avenue");
        employee.setAddressLine2("");
        employee.setCity("NewYork");
        employee.setState("NewYork");
        employee.setCountry("USA");
        employee.setZipCode("08854");
        mvc.perform(post("/v1/bfs/employees/create").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(employee)))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Date is not formatted, required : yyyy-MM-dd!!!")));


    }

    @Test(expected = Exception.class)
    public void whenPostCreateEmployeeDuplicateRecord() throws Exception {
        Employee employee = new Employee();
        employee.setId(0);
        employee.setFirstName("payPal");
        employee.setLastName("Inc");
        employee.setDateOfBirth("2020-01-01");
        employee.setAddressLine1("1 avenue");
        employee.setAddressLine2("");
        employee.setCity("NewYork");
        employee.setState("NewYork");
        employee.setCountry("USA");
        employee.setZipCode("08854");

        mvc.perform(post("/v1/bfs/employees/create").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(employee)));

        mvc.perform(post("/v1/bfs/employees/create").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(employee)))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("already exists !!!!")));
    }

    @Test
    public void whenGetRetrieveEmployee() throws Exception {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName("payPal");
        employeeEntity.setLastName("Inc");
        employeeEntity.setDateOfBirth(new java.sql.Date((new java.util.Date()).getTime()));
        employeeEntity.setAddressLine1("1 avenue");
        employeeEntity.setAddressLine2("");
        employeeEntity.setCity("NewYork");
        employeeEntity.setState("NewYork");
        employeeEntity.setCountry("USA");
        employeeEntity.setZipCode("08854");
        EmployeeEntity emp = employeeRepository.save(employeeEntity);

        mvc.perform(get("/v1/bfs/employees/get/" + emp.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.first_name", is("payPal")));
        }

}
