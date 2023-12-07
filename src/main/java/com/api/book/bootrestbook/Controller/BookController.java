package com.api.book.bootrestbook.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookServices;

@RestController
public class BookController {
	
	@Autowired
	private BookServices bookServices;
	
	@GetMapping("/book")
	public ResponseEntity<List<Book>> getBooks() {
		
		List<Book> list=bookServices.getAllBooks();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));
		
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
		Book book=bookServices.getBookById(id);
		if(book==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
	
	@PostMapping("/book")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book b;
		try {
			b=bookServices.addBook(book);
			return ResponseEntity.of(Optional.of(b));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<Void> deleteBookById(@PathVariable("id") int id) {
		try {
			this.bookServices.deleteBookById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
		}
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<Book> updateBookById(@RequestBody Book book,@PathVariable("id") int id) {
		try {
			this.bookServices.updateBookById(id,book);
			return ResponseEntity.ok().body(book);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
