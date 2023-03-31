package com.imranmadbar.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/book")
	public String index(Model model) {
		model.addAttribute("bookList", bookService.list());
		return "book/home";
	}

	@GetMapping("/book/list")
	public String list(Model model) {
		model.addAttribute("bookList", bookService.list());
		return "book/home";
	}

	@GetMapping("/book/create")
	public String create(Model model) {
		BookEntity bookObj = new BookEntity();
		model.addAttribute("bookObj", bookObj);
		return "book/bookCreate";
	}

	@PostMapping("/book/save")
	public String saveBook(BookEntity bookObj, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "book/bookCreate";
		}
		bookService.bookSave(bookObj);
		return "redirect:/book/list";
	}

	@GetMapping("/book/view/{id}")
	public String view(@PathVariable("id") long bookId, Model model) {
		BookEntity bookObj = null;
		try {
			bookObj = bookService.findById(bookId);
		} catch (Exception ex) {
			model.addAttribute("errorMessage", "Book not found");
		}
		model.addAttribute("bookObj", bookObj);
		return "book/bookView";
	}

	@GetMapping("/book/edit/{id}")
	public String eidt(Model model, @PathVariable("id") Long bookId) {
		model.addAttribute("bookObj", bookService.findById(bookId));
		return "book/bookEdit";
	}

	@PostMapping("/book/update")
	public String updateBook(Model model, BookEntity bookObj, BindingResult result) {
		if (!result.hasErrors()) {
			bookService.bookUpdate(bookObj);
		}
		return "redirect:/book/list";
	}

	@GetMapping("/book/delete/{id}")
	public String bookDelete(Model model, @PathVariable("id") Long id) {
		bookService.bookDelete(id);
		return "redirect:/book/list";
	}

}
