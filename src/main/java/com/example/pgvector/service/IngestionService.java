package com.example.pgvector.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IngestionService implements CommandLineRunner {
	
	private final VectorStore vectorStore;
	

	@Value("classpath:/docs/inventory.json")
	private Resource inventory;
	
	@Value("classpath:/docs/supplier.json")
	private Resource supplier;
	
	@Value("classpath:/docs/usage-history.json")
	private Resource usageHistory;
	
	public IngestionService(VectorStore vectorStore) {
		this.vectorStore = vectorStore;
	}
	
	/**
	 * Callback used to run the bean.
	 *
	 * @param args incoming main method arguments
	 * @throws Exception on error
	 */
	@Override
	public void run(String... args) throws Exception {
//		JsonReader jsonReaderInventory = new JsonReader(inventory);
//		TextSplitter textSplitter = new TokenTextSplitter();
//		vectorStore.accept(textSplitter.apply(jsonReaderInventory.get()));
//		log.info("Vector store loaded with data!");
	}
}
