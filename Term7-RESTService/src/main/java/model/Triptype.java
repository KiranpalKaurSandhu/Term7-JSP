package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

/*
    Author : Kiranpal Kaur
    Description : This class represents a trip type entity that is mapped to a database table named "triptypes."
     It defines the structure of trip type records.
 */
@Entity
@Table(name = "triptypes")
public class Triptype {
    @Id
    @Size(max = 1)
    @Column(name = "TripTypeId", nullable = false, length = 1)
    private String tripTypeId;

    @Size(max = 25)
    @Column(name = "TTName", length = 25)
    private String tTName;

    public String getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    public String getTTName() {
        return tTName;
    }

    public void setTTName(String tTName) {
        this.tTName = tTName;
    }

}