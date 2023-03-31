package com.imranmadbar.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.imranmadbar.role.RoleEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "users_tbl")
public class UserEntity  implements Serializable {


	private static final long serialVersionUID = 5167955767935040554L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String username;

	private String password;

	private String fullName;

	@ManyToMany( fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles_tbl", 
    joinColumns = {
	@JoinColumn(name = "user_id", referencedColumnName = "id") },
    inverseJoinColumns = {
    @JoinColumn(name = "role_id", referencedColumnName = "id") })
	private List<RoleEntity> roles = new ArrayList<RoleEntity>();

	

}
