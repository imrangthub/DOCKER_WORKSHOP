package com.imranmadbar.userRole;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "users_roles_tbl")
public class UserRoleEntity implements Serializable {

	private static final long serialVersionUID = 744591294988420567L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @Column(name = "user_id")
	private Long userId;

    @Column(name = "role_id")
	private Long roleId;;
	
	@Column(columnDefinition = "boolean default false", nullable = false)
	private boolean isDeleted;

}
