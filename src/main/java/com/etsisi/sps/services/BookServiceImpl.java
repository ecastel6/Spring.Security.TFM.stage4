package com.etsisi.sps.services;

import com.etsisi.sps.daos.BookDao;
import com.etsisi.sps.entities.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {
    private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    BookDao bookDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book findById(Integer id) {
        logger.info("BookServiceImpl.findById {}", id );
        return bookDao.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    public void update(Book book) {
        bookDao.update(book);
    }

    @Override
    public void delete(int id) {
        bookDao.delete(id);

    }

    @Override
    public void saveOrUpdate(Book book) {
        logger.info ("BookServiceImpl saveOrUpdate  {} ", book.getId());
        if (book.getId() == null) {
            logger.info("id es null llamamos a save");
            bookDao.save(book);
        } else {
            logger.info("id no es null llamamos a update");
            bookDao.update(book);
        }
    }

    @Override
    public Book searchByAuthor(String author) {
        return null;
    }
}
