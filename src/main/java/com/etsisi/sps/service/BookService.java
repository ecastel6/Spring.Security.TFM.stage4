package com.etsisi.sps.service;

import com.etsisi.sps.model.Book;

import java.util.List;

public interface BookService
{
    Book findById(Integer id);

    List<Book> findAll();

    void save(Book book);

    void update(Book book);

    void delete(int id);

    void saveOrUpdate(Book book);

    Book searchByAuthor(String author);
}
