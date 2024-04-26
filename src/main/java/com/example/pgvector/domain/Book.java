package com.example.pgvector.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "author")
	private String author;
	
	@JsonIgnore
	@Basic
	@Type(JsonType.class)
	@Column(name = "embeddings", columnDefinition = "vector")
	private List<Double> embeddings;
}
