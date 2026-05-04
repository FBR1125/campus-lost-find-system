package com.example.lostandfound.entity;

import java.time.LocalDateTime;

public class Item {
    private Integer id;
    private Integer userId;
    private String name;
    private String description;
    private Integer status; // 0:未认领 1:已认领
    private LocalDateTime createTime;
    private Integer type; // 0失物 1拾物

    public Item() {}

    public Integer getType() { return type; }
    public void setType(Integer type) { this.type = type; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
}