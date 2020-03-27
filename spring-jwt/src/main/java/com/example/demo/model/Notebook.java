package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "NoteBook")
public class Notebook {

	@Id
	private String id;
	private String name;

	@DBRef
	private List<Note> notes;

	protected Notebook() {
		this.notes = new ArrayList<>();
	}

	public Notebook(String name) {
		this();
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public int getNbOfNotes() {
		return this.notes.size();
	}

	public Note addNote(Note newNote) {
		this.notes.add(newNote);
		return newNote;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Notebook)) {
			return false;
		}
		Notebook n = (Notebook)obj;
		
		if(n.id.equalsIgnoreCase(this.id)) {
			return true;
		}
		else {
			return false;
		}
	}

}
