package com.example.pgvector.controller;

import com.example.pgvector.domain.Book;
import com.example.pgvector.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
	
	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@PostMapping("/embeddings")
	private void embeddingsBook(){
		bookService.embedding();
	}
	
	@GetMapping
	private List<Book> getListBook(@RequestParam(required = false) String keyword){
		return bookService.findAll(keyword);
	}
}
