package com.kaiburr.taskmanger.controller;

import com.kaiburr.taskmanger.model.Task;
import com.kaiburr.taskmanger.model.TaskExecution;
import com.kaiburr.taskmanger.repository.TaskRepository;
import com.kaiburr.taskmanger.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable String id) {
        return taskRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>> search(@RequestParam String name) {
        List<Task> results = taskRepo.findByNameContainingIgnoreCase(name);
        return results.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(results);
    }

    @PutMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        if (task.getCommand().contains("rm") || task.getCommand().contains("shutdown")) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(taskRepo.save(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        taskRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/run")
    public ResponseEntity<?> runCommand(@PathVariable String id) {
        Optional<Task> optTask = taskRepo.findById(id);
        if (!optTask.isPresent()) return ResponseEntity.notFound().build();

        Task task = optTask.get();
        TaskExecution exec = taskService.runCommand(task.getCommand());
        task.getTaskExecutions().add(exec);
        return ResponseEntity.ok(taskRepo.save(task));
    }
}