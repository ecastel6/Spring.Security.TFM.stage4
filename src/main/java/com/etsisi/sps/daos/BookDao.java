package com.etsisi.sps.daos;

import com.etsisi.sps.entities.Book;

import java.util.List;

public interface BookDao {

    Book findById(Integer id);

    List<Book> findAll();

    void save(Book book);

    void update(Book book);

    void delete(int id);
}
