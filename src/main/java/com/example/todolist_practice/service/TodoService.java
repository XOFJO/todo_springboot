package com.example.todolist_practice.service;

import com.example.todolist_practice.dao.JPATodoRepository;
import com.example.todolist_practice.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final JPATodoRepository JPATodoRepository;

    public TodoService(JPATodoRepository JPATodoRepository) {
        this.JPATodoRepository = JPATodoRepository;
    }

    public Todo addTodo(Todo todo) {
        return JPATodoRepository.save(todo);
    }

    public Todo getTodoById(int id) {
        Optional<Todo> todo = JPATodoRepository.findById(id);
        if (todo.isPresent()) {
            return todo.get();
        }
        throw new RuntimeException("Todo not found with id: " + id);
    }

    public List<Todo> getAllTodos() {
        return JPATodoRepository.findAll();
    }

    public Todo updateTodo(int id, Todo updatedTodo) {
        Optional<Todo> existingTodo = JPATodoRepository.findById(id);
        if (existingTodo.isPresent()) {
            Todo todo = existingTodo.get();
            if (updatedTodo.getDetail() != null) {
                todo.setDetail(updatedTodo.getDetail());
            }
            todo.setStatus(updatedTodo.isStatus());
            return JPATodoRepository.save(todo);
        }
        throw new RuntimeException("Todo not found with id: " + id);
    }

    public void deleteTodo(int id) {
        if (JPATodoRepository.existsById(id)) {
            JPATodoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Todo not found with id: " + id);
        }
    }
}
