package com.example.todolist_practice.controller.mapper;

import com.example.todolist_practice.controller.dto.TodoResponse;
import com.example.todolist_practice.model.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoMapper {
    public TodoResponse toResponse(Todo todo) {
        TodoResponse todoResponse = new TodoResponse();
        BeanUtils.copyProperties(todo, todoResponse);
        return todoResponse;
    }

    public List<TodoResponse> toResponseList(List<Todo> todos) {
        return todos.stream().map(this::toResponse).toList();
    }
}
