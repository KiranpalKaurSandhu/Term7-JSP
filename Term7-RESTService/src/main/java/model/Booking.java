package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingId", nullable = false)
    private Integer bookingId;

    @Column(name = "BookingDate")
    @Temporal(TemporalType.DATE)
    private Date bookingDate; // Change the data type to Date

    @Size(max = 50)
    @Column(name = "BookingNo", length = 50)
    private String bookingNo;

    @Column(name = "TravelerCount")
    private Integer travelerCount;

    @Column(name = "CustomerId")
    private Integer customerId;

    @Column(name = "TripTypeId")
    private String tripTypeId;

    @Column(name = "PackageId")
    private Integer packageId;

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public Integer getTravelerCount() {
        return travelerCount;
    }

    public void setTravelerCount(Integer travelerCount) {
        this.travelerCount = travelerCount;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

}