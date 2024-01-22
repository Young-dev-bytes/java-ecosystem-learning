package com.in28minutes.todorestfulapi.todo;

import com.in28minutes.todorestfulapi.todo.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoJpaController {

    private TodoService todoService;
    private TodoRepository todoRepository;

    public TodoJpaController(TodoService todoService, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }


    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        // return todoService.findByUsername(username);
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username, @PathVariable Integer id) {
        // return todoService.checkTodoExistAndReturn(username, id);
        return todoRepository.findById(id)
                .orElseThrow(()->new ToDoNotFoundException("todo not found id is: " + id));
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable Integer id) {
        // Todo todo = todoService.checkTodoExistAndReturn(username, id);
        // todoService.deleteById(todo.getId());
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username, @PathVariable Integer id, @RequestBody Todo todo) {
        // todoService.checkTodoExistAndReturn(username,id);
        // todoService.updateTodo(todo);
        return todoRepository.save(todo);
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo) {
        // todoService.updateTodo(todo);
        todo.setUsername(username);
        todo.setId(null);
        return todoRepository.save(todo);
        // return todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
    }
}
