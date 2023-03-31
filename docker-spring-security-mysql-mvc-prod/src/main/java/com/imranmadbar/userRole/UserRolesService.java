package com.imranmadbar.userRole;

import java.util.List;

import org.hibernate.PersistentObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserRolesService {

	@Autowired
	private UserRolesRepository repository;

	public List<UserRoleEntity> list() {
		return repository.list();
	}
	
	
	public UserRoleEntity findById(Long id) {
		return repository.findById(id);
	}

	
	public String roleSave(UserRoleEntity obj) {
		try {
			repository.save(obj);
			return "Role save successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}

	public String roleDelete(Long id) {
		UserRoleEntity obj=null;
		obj = repository.findByIdObj(id);
		if(obj==null) {
			return "Data not found !";
		}
		try {
			repository.delete(obj);
			return "Role delete successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}


}
