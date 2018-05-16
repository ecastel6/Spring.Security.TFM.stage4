package com.etsisi.sps.model;

import com.etsisi.sps.validators.Price;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


//@PropertySource(value = "classpath:config.properties")
@Entity
@Table(name="BOOKS")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty (message="{NotEmpty.Book.author}")
    @Column(name = "author", nullable = false)
    private String author;

    @NotEmpty
    @Column(name = "title", nullable = false)
    private String title;




    /*@NumberFormat(style = NumberFormat.Style.NUMBER)*/
    //@Pattern(regexp = "\\d+",message = "mal")
    @NotNull //@Price
    @Digits(integer = 5, fraction = 2, message="{javax.validation.constraints.Digits.message}")
    @Column(name = "price", nullable = false)
    private String price;

    @NotEmpty
    @Column(name = "description")
    private String description;

    public Book(String author, String title, String price, String description) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public Book() {
    }

    public boolean isNew() {
        return (this.id == null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title) &&
                Objects.equals(price, book.price) &&
                Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, author, title, price, description);
    }

    @Override
    public String toString() {
        return "Book[" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ']';
    }
}
