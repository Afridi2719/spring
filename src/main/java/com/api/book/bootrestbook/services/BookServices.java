package com.api.book.bootrestbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.bootrestbook.deo.BookRepository;
import com.api.book.bootrestbook.entities.Book;

@Component                                              //@Service or @Component
public class BookServices {
	
	@Autowired
	private BookRepository bookRepository;
//	private static List<Book> list=new ArrayList<>();
//	
//	static {
//		list.add(new Book(1,"java","Xyz"));
//		list.add(new Book(2,"python","Affu"));
//		list.add(new Book(3,"c++","shabbir"));
//	}
	public List<Book> getAllBooks(){
		List<Book> list=(List<Book>)this.bookRepository.findAll();
		return list;
	}
	
	public Book getBookById(int id) {
		Book book=null;
		try {
//			book= list.stream().filter(e-> e.getId()==id).findFirst().get();
			book=this.bookRepository.findById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
	public Book addBook(Book book) {
//		list.add(book);
		this.bookRepository.save(book);
		return book;
	}
	
	public void deleteBookById(int id) {
//		list=list.stream().filter(e -> e.getId()!=id).collect(Collectors.toList());
		this.bookRepository.deleteById(id);
	}

	public void updateBookById(int id,Book book) {
		
//		list=list.stream().map(e->{
//			if(e.getId()==id) {
//				e.setId(book.getId());
//				e.setTitle(book.getTitle());
//				e.setAuthor(book.getAuthor());
//			}
//			return e;
//		}).collect(Collectors.toList());
		book.setId(id);
		this.bookRepository.save(book);
	}

}
