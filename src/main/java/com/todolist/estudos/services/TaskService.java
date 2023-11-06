package com.todolist.estudos.services;

import com.todolist.estudos.dto.TaskRequest;
import com.todolist.estudos.models.Task;
import com.todolist.estudos.repositorys.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskrepository;

    private Task task;
    private List<Task> tasks;

    public List<Task> retrieveAllTodos(){
        tasks = taskrepository.findAll();

        return tasks;
    }

    public Task retrieveById(Long id){
        task = taskrepository.findById(id).orElse(null);

        return task;
    }

    public Task createTask(TaskRequest taskRequest){
        Task task = new Task();

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setCompleted(taskRequest.isCompleted());

        taskrepository.save(task);

        return task;
    }

    public Task deleteTask(Long id){
        Task task = taskrepository.findById(id).orElse(null);
        if (task != null)
            taskrepository.deleteById(id);

        return task;
    }

    public Task updateTask(Long id, TaskRequest taskRequest){
        Task task = taskrepository.findById(id).orElse(null);

        if (task != null){
            task.setTitle(taskRequest.getTitle());
            task.setDescription(taskRequest.getDescription());
            task.setCompleted(taskRequest.isCompleted());

            taskrepository.save(task);
        }

        return task;
    }

    public Task completeTask(Long id){
        Task task = taskrepository.findById(id).orElse(null);

        if (task != null ) {
            task.setCompleted(true);
            taskrepository.save(task);
        }
        return task;
    }
}
