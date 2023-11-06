package com.todolist.estudos.controllers;

import com.todolist.estudos.dto.TaskRequest;
import com.todolist.estudos.models.Task;
import com.todolist.estudos.services.TaskService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/todos")
    public ResponseEntity<List<Task>> todos (){
        List<Task> response = taskService.retrieveAllTodos();

        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/todos/{id}")
    public ResponseEntity<Task> todoById (
            @PathVariable("id") Long id
    ){
        Task response = taskService.retrieveById(id);

        if (response != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.notFound().build();
    }

    @PostMapping(value= "/todos")
    public ResponseEntity<Task> createTodo (
            @RequestBody TaskRequest task
    ){
        Task response = taskService.createTask(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(value = "/todos/{id}")
    public ResponseEntity<Task> deleteTodo(
            @PathVariable("id") Long id
    ){
        Task response = taskService.deleteTask(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "todos/{id}")
    public ResponseEntity<Task> updateTodo(
            @PathVariable("id") Long id,
            @RequestBody TaskRequest task
    ){
        Task response = taskService.updateTask(id, task);
        if (response != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = "todos/{id}/complete")
    public ResponseEntity<Task> completeTask(
            @PathVariable("id") Long id
    ){
        Task response = taskService.completeTask(id);

        if (response != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
