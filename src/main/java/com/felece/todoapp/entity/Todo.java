package com.felece.todoapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String userId;

    private String todoStatus;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(String status) {
        this.todoStatus = status;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", desc='" + description + '\'' +
                ", userId='" + userId + '\'' +
                ", status='" + todoStatus + '\'' +
                '}';
    }
}
