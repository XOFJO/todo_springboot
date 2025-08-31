package com.example.todolist_practice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String detail;
    private boolean status = false;

    public Todo() {
    }

    public Todo(String detail) {
        this.detail = detail;
    }

    public Todo(String detail, boolean status) {
        this.detail = detail;
        this.status = status;
    }

    public Todo(int id, String detail, boolean status) {
        this.id = id;
        this.detail = detail;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
