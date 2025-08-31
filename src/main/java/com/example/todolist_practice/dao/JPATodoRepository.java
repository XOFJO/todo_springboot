package com.example.todolist_practice.dao;

import com.example.todolist_practice.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPATodoRepository extends JpaRepository<Todo, Integer> {
}
