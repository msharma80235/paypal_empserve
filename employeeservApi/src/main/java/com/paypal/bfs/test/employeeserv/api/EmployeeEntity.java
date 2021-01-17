package com.paypal.bfs.test.employeeserv.api;
import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name="EMPLOYEE")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name="ADDRESS_LINE_1")
    private String addressLine1;

    @Column(name="ADDRESS_LINE_2")
    private String addressLine2;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE")
    private String state;

    @Column(name="COUNTRY")
    private String country;

    @Column(name="ZIP_CODE")
    private String zipCode;

    public EmployeeEntity(){
        super();
    }

    public EmployeeEntity(int id) {
        this.id = id;
    }

    public EmployeeEntity(String firstName, String lastName, Date dateOfBirth, String addressLine1, String addressLine2, String city, String state, String country, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public void setAddressLine1(String line1) {
        this.addressLine1 = line1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() { return zipCode; }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(addressLine1, that.addressLine1) &&
                Objects.equals(addressLine2, that.addressLine2) &&
                Objects.equals(city, that.city) &&
                Objects.equals(state, that.state) &&
                Objects.equals(country, that.country) &&
                Objects.equals(zipCode, that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth, addressLine1, addressLine2, city, state, country, zipCode);
    }
}
