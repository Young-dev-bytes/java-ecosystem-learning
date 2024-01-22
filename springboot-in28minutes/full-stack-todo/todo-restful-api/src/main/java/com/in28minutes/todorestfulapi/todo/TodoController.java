package com.in28minutes.todorestfulapi.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

// @RestController
public class TodoController {

    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    /*Todo REST API: Retrieve Todos
    @GetMapping("/users/{username}/todos")
    Retrieve Todo @GetMapping("/users/{username}/todos/{id}")
    Delete Todo @DeleteMapping("/users/{username}/todos/{id}")
    Update Todo @PutMapping("/users/{username}/todos/{id}")
    Create Todo @PostMapping("/users/{username}/todos")*/

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        return todoService.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username, @PathVariable Integer id) {
        return todoService.checkTodoExistAndReturn(username, id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable Integer id) {
        Todo todo = todoService.checkTodoExistAndReturn(username, id);
        todoService.deleteById(todo.getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username, @PathVariable Integer id, @RequestBody Todo todo) {
        todoService.checkTodoExistAndReturn(username,id);
        todoService.updateTodo(todo);
        return todo;
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo) {
        // todoService.updateTodo(todo);
        return todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
    }
}
