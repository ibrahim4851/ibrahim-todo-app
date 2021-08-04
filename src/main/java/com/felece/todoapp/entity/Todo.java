package com.felece.todoapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private int userId;

    private String todoStatus;

    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(String status) {
        this.todoStatus = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date createdAt) {
        this.date = createdAt;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", userId='" + userId + '\'' +
                ", todoStatus='" + todoStatus + '\'' +
                ", date=" + date +
                '}';
    }
}
