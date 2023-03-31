package com.imranmadbar.role;

import java.util.List;

import org.hibernate.PersistentObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class RolesService {

	@Autowired
	private RolesRepository repository;

	public List<RoleEntity> list() {
		return repository.list();
	}
	
	
	public RoleEntity findById(Long id) {
		return repository.findById(id);
	}

	public String saveOrUpdate(RoleEntity obj) {
		try {
			if (obj.getId() == null) {
				repository.save(obj);
				return "Role save successfully done !";
			}
			repository.update(obj);
			return "Role update successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}
	
	public String roleSave(RoleEntity obj) {
		try {
			repository.save(obj);
			return "Role save successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}
	
	public String roleUpdate(RoleEntity  RoleObj) {
		RoleEntity obj=null;
		obj = repository.findByIdObj(RoleObj.getId());
		if(obj==null) {
			return "Data not found !";
		}
		try {
			repository.update(RoleObj);
			return "Role update successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}
	
	public String roleDelete(Long id) {
		RoleEntity obj=null;
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
