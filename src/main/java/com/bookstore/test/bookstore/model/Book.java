package com.bookstore.test.bookstore.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "author")
	private String author;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "qty")
	private Integer qty;

	public Book(String title, String description, String author, BigDecimal price, Integer qty) {
		this.title = title;
		this.description = description;
		this.author = author;
		this.price = price;
		this.qty = qty;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", author='" + author + '\'' +
				", price=" + price +
				", qty=" + qty +
				'}';
	}
}
