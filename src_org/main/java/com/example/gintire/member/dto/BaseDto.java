package com.example.gintire.member.dto;

import com.example.gintire.member.domain.BaseEntity;

import java.time.format.DateTimeFormatter;

public abstract class BaseDto {
    private Long id;
    private String createDate;
    private String updateDate;
    protected BaseDto() {}
    protected <T extends BaseEntity> BaseDto(T entity) {
        this.id = entity.getId();
        this.createDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.updateDate = entity.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcreateDate() {
        return createDate;
    }

    public String getupdateDate() {
        return updateDate;
    }
}
