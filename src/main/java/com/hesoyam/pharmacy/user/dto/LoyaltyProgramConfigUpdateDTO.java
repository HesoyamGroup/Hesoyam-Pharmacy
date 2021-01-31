package com.hesoyam.pharmacy.user.dto;

import javax.validation.constraints.NotNull;

public class LoyaltyProgramConfigUpdateDTO {
    @NotNull(message = "Loyalty program config must be specified.")
    private Long id;
    @NotNull(message = "Checkup points must be specified.")
    private int checkUpPoints;
    @NotNull(message = "Counseling points must be specified.")
    private int counselingPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCheckUpPoints() {
        return checkUpPoints;
    }

    public void setCheckUpPoints(int checkUpPoints) {
        this.checkUpPoints = checkUpPoints;
    }

    public int getCounselingPoints() {
        return counselingPoints;
    }

    public void setCounselingPoints(int counselingPoints) {
        this.counselingPoints = counselingPoints;
    }
}
