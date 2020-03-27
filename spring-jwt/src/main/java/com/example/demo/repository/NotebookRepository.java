package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Notebook;

public interface NotebookRepository extends MongoRepository<Notebook, String> {
	
}
