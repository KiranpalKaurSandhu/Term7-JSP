package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/*
    Author : Kiranpal Kaur
    Description : This class represents a customer entity that is mapped to a database table named "customers."
     It defines the structure of customer records, including customer contact information, such as name, address, phone numbers, and email.
 */
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerId", nullable = false)
    private Integer CustomerId;

    @Size(max = 25)
    @NotNull
    @Column(name = "CustFirstName", nullable = false, length = 25)
    private String custFirstName;

    @Size(max = 25)
    @NotNull
    @Column(name = "CustLastName", nullable = false, length = 25)
    private String custLastName;

    @Size(max = 75)
    @NotNull
    @Column(name = "CustAddress", nullable = false, length = 75)
    private String custAddress;

    @Size(max = 50)
    @NotNull
    @Column(name = "CustCity", nullable = false, length = 50)
    private String custCity;

    @Size(max = 5)
    @NotNull
    @Column(name = "CustProv", nullable = false, length = 5)
    private String custProv;

    @Size(max = 7)
    @NotNull
    @Column(name = "CustPostal", nullable = false, length = 7)
    private String custPostal;

    @Size(max = 25)
    @Column(name = "CustCountry", length = 25)
    private String custCountry;

    @Size(max = 20)
    @Column(name = "CustHomePhone", length = 20)
    private String custHomePhone;

    @Size(max = 20)
    @NotNull
    @Column(name = "CustBusPhone", nullable = false, length = 20)
    private String custBusPhone;

    @Size(max = 50)
    @NotNull
    @Column(name = "CustEmail", nullable = false, length = 50)
    private String custEmail;

    @Column(name = "AgentId")
    private Integer agent;

    public Integer getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Integer customerId) {
        this.CustomerId = customerId;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustCity() {
        return custCity;
    }

    public void setCustCity(String custCity) {
        this.custCity = custCity;
    }

    public String getCustProv() {
        return custProv;
    }

    public void setCustProv(String custProv) {
        this.custProv = custProv;
    }

    public String getCustPostal() {
        return custPostal;
    }

    public void setCustPostal(String custPostal) {
        this.custPostal = custPostal;
    }

    public String getCustCountry() {
        return custCountry;
    }

    public void setCustCountry(String custCountry) {
        this.custCountry = custCountry;
    }

    public String getCustHomePhone() {
        return custHomePhone;
    }

    public void setCustHomePhone(String custHomePhone) {
        this.custHomePhone = custHomePhone;
    }

    public String getCustBusPhone() {
        return custBusPhone;
    }

    public void setCustBusPhone(String custBusPhone) {
        this.custBusPhone = custBusPhone;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public Integer getAgent() {
        return agent;
    }

    public void setAgent(Integer agent) {
        this.agent = agent;
    }

}