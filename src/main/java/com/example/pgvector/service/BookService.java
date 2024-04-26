package com.example.pgvector.service;

import com.example.pgvector.domain.Book;

import java.util.List;

public interface BookService {
	
	List<Book> findAll(String keyword);
	
	void embedding();
}
