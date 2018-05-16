package com.etsisi.sps.web;

import com.etsisi.sps.model.Book;
import com.etsisi.sps.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*
@Controller
*/
public class BookController_test
{
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public ModelAndView book() {
        return new ModelAndView("addBook", "command", new Book());
    }

    @ModelAttribute("book")
    public Book createBookModel() {
        return new Book();
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") @Validated Book book,
                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            return "addBook";
        }
        model.addAttribute("message","Successfully saved Book" + book.toString());
        model.addAttribute("author", book.getAuthor());
        model.addAttribute("title", book.getTitle());
        model.addAttribute("id", book.getId());
        bookService.save(book);
        return "result";
    }
}

