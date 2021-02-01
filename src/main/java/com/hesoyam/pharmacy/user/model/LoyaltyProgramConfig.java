package com.hesoyam.pharmacy.user.model;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

@Entity
public class LoyaltyProgramConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
    private Timestamp lastUpdated;

    @Column
    @Min(0)
    private int checkUpPoints;

    @Column
    @Min(0)
    private int counselingPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp timestamp) {
        this.lastUpdated = timestamp;
    }

    public int getCheckUpPoints() {
        return checkUpPoints;
    }

    public void setCheckUpPoints(int checkUpPrice) {
        this.checkUpPoints = checkUpPrice;
    }

    public int getCounselingPoints() {
        return counselingPoints;
    }

    public void setCounselingPoints(int counselingPrice) {
        this.counselingPoints = counselingPrice;
    }
}
