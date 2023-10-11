package model;

import jakarta.persistence.*;
import model.Booking;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "bookingdetails")
public class Bookingdetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingDetailId", nullable = false)
    private Integer id;

    @Column(name = "ItineraryNo")
    private Double itineraryNo;

    @Column(name = "TripStart")
    private Instant tripStart;

    @Column(name = "TripEnd")
    private Instant tripEnd;

    @Lob
    @Column(name = "Description")
    private String description;

    @Lob
    @Column(name = "Destination")
    private String destination;

    @Column(name = "BasePrice", precision = 19, scale = 4)
    private BigDecimal basePrice;

    @Column(name = "AgencyCommission", precision = 19, scale = 4)
    private BigDecimal agencyCommission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BookingId")
    private Booking booking;

    @Column(name = "ProductSupplierId")
    private Integer productSupplierId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getItineraryNo() {
        return itineraryNo;
    }

    public void setItineraryNo(Double itineraryNo) {
        this.itineraryNo = itineraryNo;
    }

    public Instant getTripStart() {
        return tripStart;
    }

    public void setTripStart(Instant tripStart) {
        this.tripStart = tripStart;
    }

    public Instant getTripEnd() {
        return tripEnd;
    }

    public void setTripEnd(Instant tripEnd) {
        this.tripEnd = tripEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getAgencyCommission() {
        return agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Integer getProductSupplierId() {
        return productSupplierId;
    }

    public void setProductSupplierId(Integer productSupplierId) {
        this.productSupplierId = productSupplierId;
    }

}