package com.example.pgvector.service.impl;

import com.example.pgvector.domain.Book;
import com.example.pgvector.repository.BookRepository;
import com.example.pgvector.service.BookService;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
	
	private final EmbeddingClient embeddingClient;
	
	private final BookRepository bookRepository;
	
	public BookServiceImpl(EmbeddingClient embeddingClient,
						   BookRepository bookRepository) {
		this.embeddingClient = embeddingClient;
		this.bookRepository = bookRepository;
	}
	
	@Override
	public List<Book> findAll(String keyword) {
//		String refactorKey = StringUtils.isBlank(keyword) ? null: keyword.toLowerCase();
//		String vector = refactorKey == null ? null :embeddingClient.embed(refactorKey).toString();
//		return bookRepository.findByKeyword(vector);
		return null;
	}
	
	@Override
	public void embedding() {
//		List<Book> books = bookRepository.findAll().stream()
//				.map(this::embeddingBook).collect(Collectors.toList());
//		bookRepository.saveAll(books);
	}
	
	private Book embeddingBook(Book book){
//		List<Double> embeddings = embeddingClient.embed(book.getName().toLowerCase());
//		book.setEmbeddings(embeddings);
//		return book;
		return null;
	}
}
