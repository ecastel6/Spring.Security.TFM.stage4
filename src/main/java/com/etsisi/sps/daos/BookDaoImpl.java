package com.etsisi.sps.daos;

import com.etsisi.sps.entities.Book;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookDao")
public class BookDaoImpl extends AbstractDao<Integer, Book> implements BookDao {
    static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    @Override
    public Book findById(Integer id) {
        logger.info("BookDaoImpl.findById : {}", id);
        Book book = getByKey(id);
        return book;
    }

    @Override
    public List<Book> findAll() {
        logger.info("findAll : {}");
        Criteria filter = createEntityCriteria().addOrder(Order.asc("author"));
        List<Book> books = (List<Book>) filter.list();
        return books;
    }

    @Override
    public void save(Book book) {
        logger.info("saveBook: {}", book.getAuthor());
        persist(book);
    }

    @Override
    public void update(Book book) {
        logger.info("updateBook : {}", book.toString());
        super.update(book);
    }

    @Override
    public void delete(int id) {
        logger.info("deleteBook: {}", id);
        Book book = getByKey(id);
        if (book != null) {
            delete(book);
        }
    }
}
