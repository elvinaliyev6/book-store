package com.bookstore.test.bookstore.controllers;

import com.bookstore.test.bookstore.exceptions.ResourceNotFoundException;
import com.bookstore.test.bookstore.model.Book;
import com.bookstore.test.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id)
        throws ResourceNotFoundException {
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + id));
            return ResponseEntity.ok().body(book);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try {
            Book _book = bookRepository
                    .save(new Book(
                            book.getTitle(),
                            book.getDescription(),
                            book.getAuthor(),
                            book.getPrice(),
                            book.getQty()
                            ));
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @Valid @RequestBody Book bookDetails) throws ResourceNotFoundException {
        Book bookData = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + id));
        bookData.setTitle(bookDetails.getTitle());
        bookData.setAuthor(bookDetails.getAuthor());
        bookData.setDescription(bookDetails.getDescription());
        bookData.setPrice(bookDetails.getPrice());
        bookData.setQty(bookDetails.getQty());

        final Book updatedBook = bookRepository.save(bookData);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/books/{id}")
    public Map <String, Boolean> deleteBook(@PathVariable("id") long id) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

        bookRepository.delete(book);
        Map < String, Boolean > response = new HashMap<>();
        response.put("Book deleted ::", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/books")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            bookRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/author/{author}")
    public ResponseEntity<List<Book>> findByAuthor(@PathVariable("author") String author) throws ResourceNotFoundException {
        Book bookData = bookRepository.findByAuthor(author);
        return new ResponseEntity<>(bookData, HttpStatus.OK);
    }

//    @GetMapping("/books/buy/{id}")
//    public ResponseEntity<List<Book>> buyBook(@PathVariable("id") long Id, @RequestBody Integer qty)  throws ResourceNotFoundException {
//        Book bookData = bookRepository.findAllById(Id);
//
//        if (bookData.getQty() >= qty){
//            bookData.setQty(bookData.getQty());
//            final Book updatedBook = bookRepository.save(bookData);
//            return new ResponseEntity(updatedBook, HttpStatus.OK);
//        } else {
//            return new ResourceNotFoundException("Book quantity exceeded! ");
//        }
//
//    }
}
