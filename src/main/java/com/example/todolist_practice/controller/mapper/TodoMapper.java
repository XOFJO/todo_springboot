package com.example.todolist_practice.controller.mapper;

import com.example.todolist_practice.controller.dto.TodoRequest;
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


    public Todo toEntity(TodoRequest todoRequest) {
        if (todoRequest == null) {
            return null;
        }
        return new Todo(
            todoRequest.getDetail(),
            todoRequest.isStatus()
        );
    }
}
