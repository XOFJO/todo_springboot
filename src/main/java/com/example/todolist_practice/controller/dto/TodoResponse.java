package com.example.todolist_practice.controller.dto;

public class TodoResponse {
    private int id;
    private String detail;
    private boolean completed;

    public TodoResponse() {
    }

    public TodoResponse(int id, String detail, boolean status) {
        this.id = id;
        this.detail = detail;
        this.completed = status;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean status) {
        this.completed = status;
    }
}
