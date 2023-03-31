package com.imranmadbar.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RolesController {

	@Autowired
	private RolesService rolesService;

	@GetMapping("/role")
	public String index(Model model) {
		model.addAttribute("roleList", rolesService.list());
		return "role/home";
	}

	@GetMapping("/role/list")
	public String list(Model model) {
		model.addAttribute("roleList", rolesService.list());
		return "role/home";
	}

	@GetMapping("/role/create")
	public String create(Model model) {
		RoleEntity obj = new RoleEntity();
		model.addAttribute("roleObj", obj);
		return "role/roleCreate";
	}

	@PostMapping("/role/save")
	public String saveRole(RoleEntity roleObj, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "role/roleCreate";
		}
		rolesService.roleSave(roleObj);
		return "redirect:/role/list";
	}

	@GetMapping("/role/view/{id}")
	public String view(@PathVariable("id") long roleId, Model model) {
		RoleEntity roleObj = null;
		try {
			roleObj = rolesService.findById(roleId);
		} catch (Exception ex) {
			model.addAttribute("errorMessage", "role not found");
		}
		model.addAttribute("roleObj", roleObj);
		return "role/roleView";
	}

	@GetMapping("/role/edit/{id}")
	public String eidt(Model model, @PathVariable("id") Long roleId) {
		model.addAttribute("roleObj", rolesService.findById(roleId));
		return "role/roleEdit";
	}

	@PostMapping("/role/update")
	public String updaterole(Model model, RoleEntity roleObj, BindingResult result) {
		if (!result.hasErrors()) {
			rolesService.roleUpdate(roleObj);
		}
		return "redirect:/role/list";
	}

	@GetMapping("/role/delete/{id}")
	public String roleDelete(Model model, @PathVariable("id") Long id) {
		rolesService.roleDelete(id);
		return "redirect:/role/list";
	}


}
