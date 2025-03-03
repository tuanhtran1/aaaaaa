package com.example.pgvector.controller;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

	private final VectorStore vectorStore;
	
	private final ChatClient chatClient;
	
	@Value("classpath:/prompt/template.st")
	private Resource ragPromptTemplate;
	
	
	public UserController(VectorStore vectorStore, ChatClient chatClient) {
		this.vectorStore = vectorStore;
		this.chatClient = chatClient;
	}
	
	@GetMapping("/req")
	public String getResponse(@RequestParam("question") String message) {
		List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.query(message).withTopK(2));
		List<String> contentList = similarDocuments.stream().map(Document::getContent).toList();
		PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
		Map<String, Object> promptParameters = new HashMap<>();
		promptParameters.put("input", message);
		promptParameters.put("documents", String.join("\n", contentList));
		Prompt prompt = promptTemplate.create(promptParameters);
		return chatClient.call(prompt).getResult().getOutput().getContent();
	}

}


