package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.BookEntity;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("http://localhost:3000")
public class BookController {

	@Autowired
	private BookService bookservice;

	@GetMapping("/all")
	public List<BookEntity> getAll() {
		return bookservice.getAll();

	}

	@GetMapping("/{id}")
	public ResponseEntity<BookEntity> get(@PathVariable Long id) {
		return bookservice.findBy(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public BookEntity saveOrUpdate(@RequestBody BookEntity book) {
		return bookservice.saveOrUpdate(book);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		bookservice.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookEntity> update(@PathVariable Long id, @RequestBody BookEntity book) {
		return bookservice.findBy(id).map(existingBook -> {
			existingBook.setbooktitle(book.getbooktitle());
			existingBook.setAuthor(book.getAuthor());
			existingBook.setCost(book.getCost());
			BookEntity updatedBook = bookservice.saveOrUpdate(existingBook);
			return ResponseEntity.ok(updatedBook);
		}).orElse(ResponseEntity.notFound().build());
	}

}
