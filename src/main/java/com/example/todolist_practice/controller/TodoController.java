package com.example.todolist_practice.controller;

import com.example.todolist_practice.controller.dto.TodoRequest;
import com.example.todolist_practice.model.Todo;
import com.example.todolist_practice.service.TodoService;
import com.example.todolist_practice.controller.mapper.TodoMapper;
import com.example.todolist_practice.controller.dto.TodoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:5173")
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse addTodo(@RequestBody TodoRequest todoRequest) {
        return todoMapper.toResponse(todoService.addTodo(todoMapper.toEntity(todoRequest)));
    }

    @GetMapping("/todos/{id}")
    public TodoResponse getTodoById(@PathVariable int id) {
        return todoMapper.toResponse(todoService.getTodoById(id));
    }

    @GetMapping("/todos")
    public List<TodoResponse> getTodos(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Todo> allTodos = todoService.getAllTodos();

        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, allTodos.size());

        if (startIndex >= allTodos.size()) {
            return List.of(); // Return empty list if page is beyond available data
        }

        return allTodos.subList(startIndex, endIndex)
                .stream()
                .map(todoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PutMapping("/todos/{id}")
    public TodoResponse updateTodo(@PathVariable int id, @RequestBody TodoRequest todoRequest) {
        return todoMapper.toResponse(todoService.updateTodo(id, todoMapper.toEntity(todoRequest)));
    }

    @DeleteMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
    }
}
