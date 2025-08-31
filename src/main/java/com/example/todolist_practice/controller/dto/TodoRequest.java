package com.example.todolist_practice.controller.dto;

public class TodoRequest {
    private String detail;
    private boolean status;


    public TodoRequest() {
    }

    public TodoRequest(String detail, boolean status) {
        this.detail = detail;
        this.status = status;
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
