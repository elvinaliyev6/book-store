package com.bookstore.test.bookstore.repositories;

import com.bookstore.test.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();
    Book findById(Integer id);
    Book findByTitle(String title);
    Book findByAuthor(String author);
    Book create(Book book);
    Book buyBook(String id, Integer qty);
    void update(Integer id, Book book);
    void delete(Integer id);
}
