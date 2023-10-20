package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PackageId", nullable = false)
    private Integer packageId;

    @Size(max = 50)
    @NotNull
    @Column(name = "PkgName", nullable = false, length = 50)
    private String pkgName;

    @Column(name = "PkgStartDate")
    private Instant pkgStartDate;

    @Column(name = "PkgEndDate")
    private Instant pkgEndDate;

    @Size(max = 50)
    @Column(name = "PkgDesc", length = 50)
    private String pkgDesc;

    @NotNull
    @Column(name = "PkgBasePrice", nullable = false, precision = 19, scale = 4)
    private BigDecimal pkgBasePrice;

    @Column(name = "PkgAgencyCommission", precision = 19, scale = 4)
    private BigDecimal pkgAgencyCommission;

    @NotNull
    @Column(name = "pkgImages", nullable = false)
    private Integer pkgImages;

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public Instant getPkgStartDate() {
        return pkgStartDate;
    }

    public void setPkgStartDate(Instant pkgStartDate) {
        this.pkgStartDate = pkgStartDate;
    }

    public Instant getPkgEndDate() {
        return pkgEndDate;
    }

    public void setPkgEndDate(Instant pkgEndDate) {
        this.pkgEndDate = pkgEndDate;
    }

    public String getPkgDesc() {
        return pkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        this.pkgDesc = pkgDesc;
    }

    public BigDecimal getPkgBasePrice() {
        return pkgBasePrice;
    }

    public void setPkgBasePrice(BigDecimal pkgBasePrice) {
        this.pkgBasePrice = pkgBasePrice;
    }

    public BigDecimal getPkgAgencyCommission() {
        return pkgAgencyCommission;
    }

    public void setPkgAgencyCommission(BigDecimal pkgAgencyCommission) {
        this.pkgAgencyCommission = pkgAgencyCommission;
    }

    public Integer getPkgImages() {
        return pkgImages;
    }

    public void setPkgImages(Integer pkgImages) {
        this.pkgImages = pkgImages;
    }

}