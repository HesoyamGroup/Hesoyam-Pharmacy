package com.hesoyam.pharmacy.feedback.dto;

public class ComplaintCreatedResponseDTO {
    private Long id;
    private String entityName;
    private String body;

    public ComplaintCreatedResponseDTO(){
        //Empty ctor for JSON serializer
    }

    public ComplaintCreatedResponseDTO(Long id, String entityName, String body) {
        this.id = id;
        this.entityName = entityName;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
