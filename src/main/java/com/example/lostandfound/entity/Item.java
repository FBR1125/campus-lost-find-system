package com.example.lostandfound.entity;

import java.time.LocalDateTime;

public class Item {
    private Integer id;
    private Integer userId;
    private String name;
    private String place;       // 新增：地点
    private String description; // 详细描述（可选）
    private String contact;     // 新增：联系方式
    private Integer type;       // 0丢失 1捡到
    private Integer status;     // 0待认领 1已认领
    private LocalDateTime createTime;
    private Integer checkStatus; // 0未审核 1通过 2不通过

    // Getter & Setter
    public Integer getCheckStatus() { return checkStatus; }
    public void setCheckStatus(Integer checkStatus) { this.checkStatus = checkStatus; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public Integer getType() { return type; }
    public void setType(Integer type) { this.type = type; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}