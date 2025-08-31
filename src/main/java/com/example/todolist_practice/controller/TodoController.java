package com.example.todolist_practice.controller;

import com.example.todolist_practice.model.Todo;
import com.example.todolist_practice.service.TodoService;
import com.example.todolist_practice.controller.mapper.TodoMapper;
import com.example.todolist_practice.controller.dto.TodoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse addTodo(@RequestBody Todo todo) {
        return todoMapper.toResponse(todoService.addTodo(todo));
    }

    @GetMapping("/todos/{id}")
    public TodoResponse getTodoById(@PathVariable int id) {
        return todoMapper.toResponse(todoService.getTodoById(id));
    }

    @GetMapping("/todos")
    public List<TodoResponse> getTodos() {
        return todoService.getAllTodos()
                .stream()
                .map(todoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PutMapping("/todos/{id}")
    public TodoResponse updateTodo(@PathVariable int id, @RequestBody Todo updatedTodo) {
        return todoMapper.toResponse(todoService.updateTodo(id, updatedTodo));
    }

    @DeleteMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
    }
}
