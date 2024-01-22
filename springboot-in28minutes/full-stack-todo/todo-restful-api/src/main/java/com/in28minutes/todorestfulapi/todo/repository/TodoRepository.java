package com.in28minutes.todorestfulapi.todo.repository;


import com.in28minutes.todorestfulapi.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findByUsername(String username);

}
