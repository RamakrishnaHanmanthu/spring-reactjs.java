package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BookEntity;
import com.example.demo.repository.Bookrepository;

@Service
public class BookService {

	@Autowired
	private Bookrepository bookrepository;


	public void deleteById(Long id) {
		bookrepository.deleteById(id);
	}

	public List<BookEntity> getAll() {
		return bookrepository.findAll();
	}

	public Optional<BookEntity> findBy(Long id) {
		return bookrepository.findById(id);
	}

	public BookEntity saveOrUpdate(BookEntity book) {
		
		return bookrepository.save(book);
	}
	
	
}