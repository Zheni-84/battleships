package bg.softuni.battleships.controller;

import bg.softuni.battleships.model.dto.UserLoginDTO;
import bg.softuni.battleships.model.dto.UserRegistrationDTO;
import bg.softuni.battleships.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@ModelAttribute("registrationDTO")
	public UserRegistrationDTO initRegistration() {
		return new UserRegistrationDTO();
	}

	@ModelAttribute("loginDTO")
	public UserLoginDTO initLogin() {
		return new UserLoginDTO();
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid UserRegistrationDTO registrationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors() || !authService.register(registrationDTO)) {
			redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO", bindingResult);

			return "redirect:/register";
		}

		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		//create this method
		//go to html - add thymeleaf, th:action/method, th:object, th:field, errorclass
		//create DTO class
		// create ModelAttribute for the DTO object
		//create PostMapping
		return "login";
	}

	@PostMapping("/login")
	public String login(@Valid UserLoginDTO loginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

			return "redirect:/login";
		}
		if (!this.authService.login(loginDTO)) {
			redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
			redirectAttributes.addFlashAttribute("badCredentials", true);

			return "redirect:/login";
		}

		return "redirect:/home";
	}

	@GetMapping("/logout")
	public String logout(){
		this.authService.logout();
		return "redirect:/";
	}
}