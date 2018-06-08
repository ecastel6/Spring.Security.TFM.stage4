package com.etsisi.sps.validators;

import com.etsisi.sps.entities.Book;
import com.etsisi.sps.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class BookFormValidator implements Validator {

	@Autowired
	BookService bookService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Book book = (Book) target;
        String PRICE_PATTERN = "[.0-9]+";

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "NotEmpty.bookForm.author");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.bookForm.title");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.bookForm.price");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.bookForm.description");

		// numeric values only
        if (book.getPrice() != null) {
            Pattern pattern = Pattern.compile(PRICE_PATTERN);
            Matcher matcher = pattern.matcher(book.getPrice().toString());
            if (!matcher.matches()) {
                errors.rejectValue("price", "NotEmpty.bookForm.number");
            }
        }
	}
}