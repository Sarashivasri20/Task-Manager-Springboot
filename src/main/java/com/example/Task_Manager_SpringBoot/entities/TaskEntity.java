package com.example.Task_Manager_SpringBoot.entities;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class TaskEntity {
    private int id;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;

}
