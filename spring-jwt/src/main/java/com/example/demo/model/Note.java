package com.example.demo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Note")
public class Note {
	
	
	@Id
	private String id;
	private String title;
	private String text;
	
	private String notebookId;
	
	private Date lastModifiedOn;
	
	protected Note() {
        this.lastModifiedOn = new Date();
    }
	
	public Note(String title, String text, String notebookId) {
        this();
        this.title = title;
        this.text = text;
        this.notebookId = notebookId;
    }
	
	public Note(String title, String text, String notebookId, Date date) {
        this.title = title;
        this.text = text;
        this.notebookId = notebookId;
        this.lastModifiedOn = date;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNotebookId() {
		return notebookId;
	}

	public void setNotebookId(String notebookId) {
		this.notebookId = notebookId;
	}

	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}

	public void setLastModifiedOn(Date lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Note)) {
			return false;
		}
		Note n = (Note)obj;
		
		if(n.id.equalsIgnoreCase(this.id)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
}
