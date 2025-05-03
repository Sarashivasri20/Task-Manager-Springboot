package com.example.Task_Manager_SpringBoot.controllers;

import com.example.Task_Manager_SpringBoot.dto.CreateNoteDTO;
import com.example.Task_Manager_SpringBoot.dto.CreateNoteResponseDTO;
import com.example.Task_Manager_SpringBoot.entities.NoteEntity;
import com.example.Task_Manager_SpringBoot.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

    private NoteService notesService;
    public NotesController(NoteService notesService){
        this.notesService=notesService;
    }
    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId){
        var notes=notesService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }
    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNote(
        @PathVariable("taskId") Integer taskId,
        @RequestBody CreateNoteDTO body
    ){
            var note= notesService.addNoteForTask(taskId,body.getTitle(),body.getBody());
            return ResponseEntity.ok(new CreateNoteResponseDTO(taskId,note));
        }
    }

