package com.example.Task_Manager_SpringBoot.entities;

import lombok.Data;

@Data
public class NoteEntity {
    private int id;
    private String title;
    private String body;
}
