package com.example.Task_Manager_SpringBoot.services;

import com.example.Task_Manager_SpringBoot.entities.TaskEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;

@Service
public class TaskServices {
    private ArrayList<TaskEntity> tasks= new ArrayList<>();
    private int taskId=1;

    public TaskEntity addTask(String title, String description, String deadline){
        TaskEntity task= new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        //task.setDeadline(new Date(deadline));
        task.setCompleted(false);
        tasks.add(task);
        taskId++;
        return task;
    }
    public ArrayList<TaskEntity> getTasks(){
        return tasks;
    }

    public TaskEntity getTaskById(Integer id){
        for(TaskEntity task : tasks){
            if(task.getId()==id){
                return task;
            }
        }
        return null;
    }


}
