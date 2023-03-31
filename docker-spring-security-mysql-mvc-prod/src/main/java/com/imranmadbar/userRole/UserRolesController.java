package com.imranmadbar.userRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imranmadbar.role.RolesService;
import com.imranmadbar.user.UserService;


@Controller
public class UserRolesController {

	@Autowired
	private UserRolesService userRolesService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RolesService rolesService;

	@GetMapping("/user-role")
	public String index(Model model) {
		model.addAttribute("userRoleList", userRolesService.list());
		return "userRole/home";
	}

	@GetMapping("/user-role/list")
	public String list(Model model) {
		model.addAttribute("userRoleList", userRolesService.list());
		return "userRole/home";
	}

	@GetMapping("/user-role/create")
	public String create(Model model) {
		UserRoleEntity obj = new UserRoleEntity();
		model.addAttribute("roleList", rolesService.list());
		model.addAttribute("userList", userService.list());
		model.addAttribute("userRoleObj", obj);
		return "userRole/userRoleCreate";
	}

	@PostMapping("/user-role/save")
	public String saveUserRole(UserRoleEntity userRoleObj, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "userRole/userRoleCreate";
		}
		userRolesService.roleSave(userRoleObj);
		return "redirect:/user-role/list";
	}

	@GetMapping("/user-role/delete/{id}")
	public String roleDelete(Model model, @PathVariable("id") Long id) {
		userRolesService.roleDelete(id);
		return "redirect:/user-role/list";
	}


}
