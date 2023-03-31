package com.imranmadbar.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/user")
	public String index(Model model) {
		model.addAttribute("userList", userService.list());
		return "user/home";
	}

	@GetMapping("/user/list")
	public String list(Model model) {
		model.addAttribute("userList", userService.list());
		return "user/home";
	}

	@GetMapping("/user/create")
	public String create(Model model) {
		UserEntity obj = new UserEntity();
		model.addAttribute("userObj", obj);
		return "user/userCreate";
	}

	@PostMapping("/user/save")
	public String saveuser(UserEntity userObj, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user/userCreate";
		}
		userService.userSave(userObj);
		return "redirect:/user/list";
	}

	@GetMapping("/user/view/{id}")
	public String view(@PathVariable("id") long userId, Model model) {
		UserEntity userObj = null;
		try {
			userObj = userService.findById(userId);
		} catch (Exception ex) {
			model.addAttribute("errorMessage", "user not found");
		}
		model.addAttribute("userObj", userObj);
		return "user/userView";
	}

	@GetMapping("/user/edit/{id}")
	public String eidt(Model model, @PathVariable("id") Long userId) {
		model.addAttribute("userObj", userService.findById(userId));
		return "user/userEdit";
	}

	@PostMapping("/user/update")
	public String updateuser(Model model, UserEntity userObj, BindingResult result) {
		if (!result.hasErrors()) {
			userService.userUpdate(userObj);
		}
		return "redirect:/user/list";
	}

	@GetMapping("/user/delete/{id}")
	public String userDelete(Model model, @PathVariable("id") Long id) {
		userService.userDelete(id);
		return "redirect:/user/list";
	}


}
