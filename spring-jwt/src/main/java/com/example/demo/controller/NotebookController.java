package com.example.demo.controller;

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
@RequestMapping("/api/notebooks")
@CrossOrigin
public class NotebookController {

	@Autowired
	private NotebookRepository notebookRepository;
	@Autowired
	private NoteRepository noteRepository;

	@GetMapping("/all")
	public List<Notebook> all() {
		List<Notebook> notebooks = this.notebookRepository.findAll();

		return notebooks;
	}

	@GetMapping("/byId/{id}")
	public Notebook byId(@PathVariable String id) {
		Notebook notebook = this.notebookRepository.findById(id).orElse(null);

		if (notebook == null) {
			return new Notebook("Notebook Not Found");
		}

		return notebook;
	}

	@PostMapping
	public Notebook save(@RequestBody Notebook newNotebook) {

		// save notebookEntity instance to db
		this.notebookRepository.save(newNotebook);

		return newNotebook;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		Notebook notebook = notebookRepository.findById(id).orElse(null);
		
		if(notebook == null) {
			return;
		}
		if(notebook.getNbOfNotes() == 0) {
			this.notebookRepository.deleteById(id);
			return;
		}
		
		
		
		List<Note> notes = notebook.getNotes();
		
		notes.forEach((note)->{
			this.noteRepository.deleteById(note.getId());
		});
		
		this.notebookRepository.deleteById(id);
		
		
	}

}
