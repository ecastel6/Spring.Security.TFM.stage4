package com.etsisi.sps.controllers;

import com.etsisi.sps.entities.Book;
import com.etsisi.sps.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

//http://www.tikalk.com/redirectattributes-new-feature-spring-mvc-31/
//https://en.wikipedia.org/wiki/Post/Redirect/Get
//http://www.oschina.net/translate/spring-mvc-flash-attribute-example

@Controller
public class BookController
{

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    /*@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model entities) {
        logger.debug("index()");
        return "redirect:/books";
    }*/

    // list page
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String showAllBooks(Model model) {
        logger.info("BookList()");
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    // save or update book
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String saveOrUpdateBook(@ModelAttribute("bookForm") @Validated Book book,
                                   BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

        logger.info("books/post saveOrUpdateBook() : {}", book.toString());

        if (result.hasErrors()) {
/*
			populateDefaultModel(entities);
*/
            return "books/bookform";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if (book.isNew()) {
                logger.info("book.isNew = true");
                redirectAttributes.addFlashAttribute("msg", "Book added successfully!");
            } else {
                logger.info ("BookController.saveOrUpdateBook.isNew false");
                redirectAttributes.addFlashAttribute("msg", "Book updated successfully!");
            }

            bookService.saveOrUpdate(book);
            logger.info("bookid {}", book.getId());
            // POST/REDIRECT/GET
            return "redirect:/books/" + book.getId();

            // POST/FORWARD/GET
            // return "book/list";

        }

    }

    // show add book form
    @RequestMapping(value = "/books/add", method = RequestMethod.GET)
    public String showAddBookForm(Model model) {

        logger.info("showAddBookForm()");

        Book book = new Book();

        /*// set default value
        book.setAuthor("Enter author");
        book.setTitle("Enter title");
        book.setPrice("0.0");
        book.setDescription("Please suply a brief description");*/

        model.addAttribute("bookForm", book);
/*
		populateDefaultModel(entities);
*/

        return "books/bookform";

    }

    // show update form
    @RequestMapping(value = "/books/{id}/update", method = RequestMethod.GET)
    public String showUpdateBookForm(@PathVariable("id") int id, Model model) {

        logger.info("showUpdateBookForm() : {}", id);

        Book book = bookService.findById(id);
        model.addAttribute("bookForm", book);
		
/*
		populateDefaultModel(entities);
*/

        return "books/bookform";

    }

    // delete book
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/books/{id}/delete", method = RequestMethod.POST)
    public String deleteBook(@PathVariable("id") int id,
                             final RedirectAttributes redirectAttributes) {

        logger.info("deleteBook() : {}", id);

        bookService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Book is deleted!");

        return "redirect:/books";

    }

    // show book
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public String showBook(@PathVariable("id") int id, Model model) {

        logger.info("showBook() id: {}", id);

        Book book = bookService.findById(id);
        if (book == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Book not found");
        }
        model.addAttribute("book", book);

        return "books/show";

    }
/*
	private void populateDefaultModel(Model entities) {

		List<String> frameworksList = new ArrayList<String>();
		frameworksList.add("Spring MVC");
		frameworksList.add("Struts 2");
		frameworksList.add("JSF 2");
		frameworksList.add("GWT");
		frameworksList.add("Play");
		frameworksList.add("Apache Wicket");
		entities.addAttribute("frameworkList", frameworksList);

		Map<String, String> skill = new LinkedHashMap<String, String>();
		skill.put("Hibernate", "Hibernate");
		skill.put("Spring", "Spring");
		skill.put("Struts", "Struts");
		skill.put("Groovy", "Groovy");
		skill.put("Grails", "Grails");
		entities.addAttribute("javaSkillList", skill);

		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		entities.addAttribute("numberList", numbers);

		Map<String, String> country = new LinkedHashMap<String, String>();
		country.put("US", "United Stated");
		country.put("CN", "China");
		country.put("SG", "Singapore");
		country.put("MY", "Malaysia");
		entities.addAttribute("countryList", country);

	}*/

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

        logger.info("handleEmptyData()");
        logger.info("Request: {}, error ", req.getRequestURL(), ex);

        ModelAndView model = new ModelAndView();
        model.setViewName("book/show");
        model.addObject("msg", "book not found");

        return model;

    }

}