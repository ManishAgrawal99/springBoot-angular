package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Note;
import com.example.demo.model.Notebook;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.NotebookRepository;


@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController {

	@Autowired
	private NoteRepository noteRepository;
    @Autowired
	private NotebookRepository notebookRepository;
	
    
    
    @GetMapping("/all")
    public List<Note> all() {
        List<Note> notes = this.noteRepository.findAll();

        return notes;
    }
    
    
    
    @GetMapping("/byId/{id}")
    public Note byId(@PathVariable String id) {
        Note note = this.noteRepository.findById(id).orElse(null);

        if (note == null) {
        	return new Note("title", "text", "404");
        }

        return note;
    }
    
    
    
    @GetMapping("/byNotebook/{notebookId}")
    public List<Note> byNotebook(@PathVariable String notebookId) {
        List<Note> notes = new ArrayList<>();

        Notebook notebook = this.notebookRepository.findById(notebookId).orElse(null);
        if (notebook!=null) {
            notes = this.noteRepository.findAllByNotebookId(notebook.getId());
        }
 
        return notes;
    }
    
    
    
    @PostMapping
    public Note save(@RequestBody Note newNote) {
        // save note instance to db
    	Notebook notebook = notebookRepository.findById(newNote.getNotebookId()).orElse(null);
    	if(notebook==null) {
    		return new Note("title", "text", "404");
    	}
    	
    	
        Note savedNote = this.noteRepository.save(newNote);
        
        if(!notebook.getNotes().contains(newNote)) {
        	notebook.addNote(savedNote);
            notebookRepository.save(notebook);
        }
        
        return savedNote;
    }
    
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
    	
    	Note savedNote = noteRepository.findById(id).orElse(null);
    	if(savedNote == null) {
    		return;
    	}
    	
    	Notebook notebook = notebookRepository.findById(savedNote.getNotebookId()).orElse(null);
    	if(notebook != null) {
    		List<Note> notes = notebook.getNotes();
    		notes.remove(savedNote);
    		notebook.setNotes(notes);
    		notebookRepository.save(notebook);
    	}
    	
        this.noteRepository.deleteById(id);
    }
}
