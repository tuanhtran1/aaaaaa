package com.example.pgvector.repository;

import com.example.pgvector.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Query(value = "select * from book b where :vector is null "
			+ "or 1 - (b.embeddings <=> cast(:vector as vector)) > 0.83 "
			+ "order by 1 - (b.embeddings <=> cast(:vector as vector)) asc", nativeQuery = true)
	List<Book> findByKeyword(@Param("vector") String vector);
}
