package com.example.Task_Manager_SpringBoot.controllers;

import com.example.Task_Manager_SpringBoot.dto.CreateTaskDTO;
import com.example.Task_Manager_SpringBoot.dto.ErrorResponseDTO;
import com.example.Task_Manager_SpringBoot.dto.TaskResponseDTO;
import com.example.Task_Manager_SpringBoot.dto.UpdateTaskDTO;
import com.example.Task_Manager_SpringBoot.entities.TaskEntity;
import com.example.Task_Manager_SpringBoot.services.NoteService;
import com.example.Task_Manager_SpringBoot.services.TaskServices;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskServices taskService;
    private final NoteService notesService;
    private ModelMapper modelMapper =new ModelMapper();
    public TaskController(TaskServices taskService, NoteService notesService){
        this.taskService=taskService;
        this.notesService=notesService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        var tasks= taskService.getTasks();
        return ResponseEntity.ok(tasks);

    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Integer id){
        var task= taskService.getTaskById(id);
        var notes=notesService.getNotesForTask(id);
        if(task==null){
            return ResponseEntity.notFound().build();
        }
        var taskResponse= modelMapper.map(task, TaskResponseDTO.class);
        taskResponse.setNotes(notes);
        return ResponseEntity.ok(taskResponse);

    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task=taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body) throws ParseException{
        var task=taskService.updateTask(id,body.getDescription(),body.getDeadline(),body.isCompleted());
        if(task ==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format"));
        }
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));

    }


}
