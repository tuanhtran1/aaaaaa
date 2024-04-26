package com.example.pgvector.service.impl;

import com.example.pgvector.domain.Book;
import com.example.pgvector.repository.BookRepository;
import com.example.pgvector.service.BookService;
import com.example.pgvector.utils.TextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
		String refactorKey = StringUtils.isBlank(keyword) ? null: keyword.toLowerCase();
		String vector = refactorKey == null ? null :embeddingClient.embed(refactorKey).toString();
		return bookRepository.findByKeyword(vector);
	}
	
	@Override
	public void embedding() {
		List<Book> books = bookRepository.findAll().stream()
				.map(this::embeddingBook).collect(Collectors.toList());
		bookRepository.saveAll(books);
	}
	
	private Book embeddingBook(Book book){
		List<Double> embeddings = embeddingClient.embed(book.getName().toLowerCase());
		book.setEmbeddings(embeddings);
		return book;
	}
}
