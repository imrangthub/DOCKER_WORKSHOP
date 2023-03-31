package com.imranmadbar.userRole;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRolesRepository {

	@Autowired
	private EntityManager entityManager;

	public UserRoleEntity findById(Long id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserRoleEntity> criteria = builder.createQuery(UserRoleEntity.class);
		Root<UserRoleEntity> root = criteria.from(UserRoleEntity.class);
		criteria.select(root).where(builder.equal(root.get("id"), id), builder.equal(root.get("isDeleted"), false));
		return entityManager.createQuery(criteria).getSingleResult();
	}

	public UserRoleEntity findByIdObj(Long id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserRoleEntity> criteria = builder.createQuery(UserRoleEntity.class);
		Root<UserRoleEntity> root = criteria.from(UserRoleEntity.class);
		criteria.select(root).where(builder.equal(root.get("id"), id));
		try {
			return entityManager.createQuery(criteria).getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public UserRoleEntity findByIdAllItem(Long id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserRoleEntity> criteria = builder.createQuery(UserRoleEntity.class);
		Root<UserRoleEntity> root = criteria.from(UserRoleEntity.class);
		criteria.select(root).where(builder.equal(root.get("id"), id));
		return entityManager.createQuery(criteria).getSingleResult();
	}

	public List<UserRoleEntity> list() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserRoleEntity> criteria = builder.createQuery(UserRoleEntity.class);
		Root<UserRoleEntity> root = criteria.from(UserRoleEntity.class);
		criteria.select(root).where(builder.equal(root.get("isDeleted"), false));
		return entityManager.createQuery(criteria).getResultList();
	}

	public List<UserRoleEntity> remove() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserRoleEntity> criteria = builder.createQuery(UserRoleEntity.class);
		Root<UserRoleEntity> root = criteria.from(UserRoleEntity.class);
		criteria.select(root).where(builder.equal(root.get("isDeleted"), true));
		return entityManager.createQuery(criteria).getResultList();
	}

	public Boolean save(UserRoleEntity obj) {
		try {
			entityManager.persist(obj);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	
	public Boolean delete(UserRoleEntity obj) {
		try {
			entityManager.remove(obj);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}



}
