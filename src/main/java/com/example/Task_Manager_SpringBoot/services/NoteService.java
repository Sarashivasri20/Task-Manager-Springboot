package com.example.Task_Manager_SpringBoot.services;

import com.example.Task_Manager_SpringBoot.entities.NoteEntity;
import com.example.Task_Manager_SpringBoot.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class NoteService {
    private TaskServices taskServices;
    private HashMap<Integer, TaskNotesHolder> taskNoteHolders=new HashMap<>();


    public NoteService(TaskServices taskServices){
        this.taskServices=taskServices;
    }
    class TaskNotesHolder{
        protected int noteId=1;
        protected ArrayList<NoteEntity> notes= new ArrayList<>();
    }
    public List<NoteEntity> getNotesForTask(int taskId){
        TaskEntity task= taskServices.getTaskById(taskId);
        if(task==null){
            return null;
        }
        if(taskNoteHolders.get(taskId)==null){
            taskNoteHolders.put(taskId,new TaskNotesHolder());
        }
        return taskNoteHolders.get(taskId).notes;
    }
    public NoteEntity addNoteForTask(int taskId,String title, String body){
        TaskEntity task= taskServices.getTaskById(taskId);
        if(task==null){
            return null;
        }
        if(taskNoteHolders.get(taskId)==null){
            taskNoteHolders.put(taskId, new TaskNotesHolder());
        }
        TaskNotesHolder taskNotesHolder=taskNoteHolders.get(taskId);
        NoteEntity note=new NoteEntity();
        note.setId(taskNotesHolder.noteId);
        note.setTitle(title);
        note.setBody(body);
        taskNotesHolder.notes.add(note);
        taskNotesHolder.noteId++;
        return note;

    }
}
