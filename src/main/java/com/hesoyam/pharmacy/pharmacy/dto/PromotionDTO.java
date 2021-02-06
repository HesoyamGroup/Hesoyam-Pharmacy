package com.hesoyam.pharmacy.pharmacy.dto;

import com.hesoyam.pharmacy.pharmacy.model.Promotion;
import com.hesoyam.pharmacy.user.dto.UserBasicInfoDTO;
import com.hesoyam.pharmacy.util.DateTimeRange;

public class PromotionDTO {
    private Long id;
    private String title;
    private String description;
    private DateTimeRange range;
    private UserBasicInfoDTO administrator;

    public PromotionDTO(){
        //Empty ctor for JSON serializer
    }

    public PromotionDTO(Promotion promotion){
        this.id = promotion.getId();
        this.title = promotion.getTitle();
        this.description = promotion.getDescription();
        this.range = promotion.getDateTimeRange();
        this.administrator = new UserBasicInfoDTO(promotion.getAdministrator());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTimeRange getRange() {
        return range;
    }

    public void setRange(DateTimeRange range) {
        this.range = range;
    }

    public UserBasicInfoDTO getAdministrator() {
        return administrator;
    }

    public void setAdministrator(UserBasicInfoDTO administrator) {
        this.administrator = administrator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
