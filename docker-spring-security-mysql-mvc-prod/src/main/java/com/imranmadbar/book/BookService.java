package com.imranmadbar.book;

import java.util.List;
import org.hibernate.PersistentObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
	
	public String helpMsg() {
		return "Welcome CRUD Book API !\n\n"
				+ "API List:\n[ "
				+ ""
				+ "1) Show Book List: http://localhost:8181/book/list\n\n"
				+ "2) Find By Id: http://localhost:8181/book/find-by-id?id=1\n\n"
				+ "3) Save Book: http://localhost:8181/book/save?name=Java&&type=Programming\n\n"
				+ "4) Update Book: http://localhost:8181/book/update?id=1&&name=Java&&type=Programming\n\n"
				+ "5) Delete Book: http://localhost:8181/book/delete?id=1"
				+ ""
				+ " ]";
	}

	public List<BookEntity> list() {
		return repository.list();
	}
	
	
	public BookEntity findById(Long id) {
		return repository.findById(id);
	}

	public String findByName(String userName) {
		return repository.findByName(userName);
	}

	public String saveOrUpdate(BookEntity obj) {
		try {
			if (obj.getId() == null) {
				repository.save(obj);
				return "Book save successfully done !";
			}
			repository.update(obj);
			return "Book update successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}
	
	public String bookSave(BookEntity obj) {
		try {
			repository.save(obj);
			return "Book save successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}
	
	public String bookUpdate(BookEntity  bookObj) {
		BookEntity obj=null;
		obj = repository.findByIdObj(bookObj.getId());
		if(obj==null) {
			return "Data not found !";
		}
		try {
			repository.update(bookObj);
			return "Book update successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}
	
	public String bookDelete(Long id) {
		BookEntity obj=null;
		obj = repository.findByIdObj(id);
		if(obj==null) {
			return "Data not found !";
		}
		try {
			repository.delete(obj);
			return "Book delete successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}



}
