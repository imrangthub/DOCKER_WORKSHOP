package com.imranmadbar.user;

import java.util.List;

import org.hibernate.PersistentObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserEntity findById(Long id) {
		return userRepository.findById(id);
	}

	public UserEntity findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public String saveOrUpdate(UserEntity obj) {
		try {
			if (obj.getId() == null) {
				userRepository.save(obj);
				return "User save successfully done !";
			}
			userRepository.update(obj);
			return "User update successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}

	public String userSave(UserEntity obj) {
		try {
			userRepository.save(obj);
			return "User save successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}

	public String userUpdate(UserEntity UserObj) {
		UserEntity obj = null;
		obj = userRepository.findByIdObj(UserObj.getId());
		if (obj == null) {
			return "Data not found !";
		}
		try {
			userRepository.update(UserObj);
			return "User update successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}

	public String userDelete(Long id) {
		UserEntity obj = null;
		obj = userRepository.findByIdObj(id);
		if (obj == null) {
			return "Data not found !";
		}
		try {
			userRepository.delete(obj);
			return "User delete successfully done !";
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistentObjectException(ex.getCause().toString());
		}
	}

	public List<UserEntity> list() {
		return userRepository.list();
	}

}
