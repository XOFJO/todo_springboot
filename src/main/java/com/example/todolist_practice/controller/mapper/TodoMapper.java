package com.example.todolist_practice.controller.mapper;

import com.example.todolist_practice.controller.dto.TodoResponse;
import com.example.todolist_practice.model.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public TodoResponse toResponse(Todo todo) {
        if (todo == null) {
            return null;
        }
        return new TodoResponse(
            todo.getId(),
            todo.getDetail(),
            todo.isStatus()
        );
    }

    public Todo toEntity(TodoResponse todoResponse) {
        if (todoResponse == null) {
            return null;
        }
        return new Todo(
            todoResponse.getId(),
            todoResponse.getDetail(),
            todoResponse.isStatus()
        );
    }
}
