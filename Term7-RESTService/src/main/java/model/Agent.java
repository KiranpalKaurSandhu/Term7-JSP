package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

/*
    Author : Kiranpal Kaur
    Description :  This class represents an agent who works for a travel agency. It is mapped to a database
    table named "agents" and defines properties such as agent's first name, middle initial, last name, business
    phone, email, position, and references to the agency. Additionally, it includes login credentials
     (username and password) for agent authentication.
 */
@Entity
@Table(name = "agents")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AgentId", nullable = false)
    private Integer agentId;

    @Size(max = 20)
    @Column(name = "AgtFirstName", length = 20)
    private String agtFirstName;

    @Size(max = 5)
    @Column(name = "AgtMiddleInitial", length = 5)
    private String agtMiddleInitial;

    @Size(max = 20)
    @Column(name = "AgtLastName", length = 20)
    private String agtLastName;

    @Size(max = 20)
    @Column(name = "AgtBusPhone", length = 20)
    private String agtBusPhone;

    @Size(max = 50)
    @Column(name = "AgtEmail", length = 50)
    private String agtEmail;

    @Size(max = 20)
    @Column(name = "AgtPosition", length = 20)
    private String agtPosition;

    @Column(name = "AgencyId")
    private Integer agency;

    @Size(max = 20)
    @Column(name = "username", length = 20)
    private String username;

    @Size(max = 20)
    @Column(name = "password", length = 20)
    private String password;

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId= agentId;
    }

    public String getAgtFirstName() {
        return agtFirstName;
    }

    public void setAgtFirstName(String agtFirstName) {
        this.agtFirstName = agtFirstName;
    }

    public String getAgtMiddleInitial() {
        return agtMiddleInitial;
    }

    public void setAgtMiddleInitial(String agtMiddleInitial) {
        this.agtMiddleInitial = agtMiddleInitial;
    }

    public String getAgtLastName() {
        return agtLastName;
    }

    public void setAgtLastName(String agtLastName) {
        this.agtLastName = agtLastName;
    }

    public String getAgtBusPhone() {
        return agtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone) {
        this.agtBusPhone = agtBusPhone;
    }

    public String getAgtEmail() {
        return agtEmail;
    }

    public void setAgtEmail(String agtEmail) {
        this.agtEmail = agtEmail;
    }

    public String getAgtPosition() {
        return agtPosition;
    }

    public void setAgtPosition(String agtPosition) {
        this.agtPosition = agtPosition;
    }

    public Integer getAgency() {
        return agency;
    }

    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}