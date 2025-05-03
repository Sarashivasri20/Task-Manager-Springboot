package com.example.Task_Manager_SpringBoot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskDTO {
    String description;
    String deadline;
    boolean completed;
}
