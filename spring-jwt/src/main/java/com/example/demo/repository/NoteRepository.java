package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Note;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
	List<Note> findAllByNotebookId(String notebookId);
}
