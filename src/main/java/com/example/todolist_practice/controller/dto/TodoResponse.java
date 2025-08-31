package com.example.todolist_practice.controller.dto;

import com.example.todolist_practice.model.TodoList;

public class TodoResponse {
    private int id;
    private String detail;
    private boolean status;
    private TodoList todoList;

    public TodoResponse() {

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

    public TodoList getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }
}

