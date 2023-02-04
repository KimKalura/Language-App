package com.spring.languageapp.dto;

public class QuoteResponseDTO {

    private Boolean isApproved;
    private Long adminId;

    public QuoteResponseDTO(){}

    public QuoteResponseDTO(Boolean isApproved, Long adminId) {
        this.isApproved = isApproved;
        this.adminId = adminId;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}
