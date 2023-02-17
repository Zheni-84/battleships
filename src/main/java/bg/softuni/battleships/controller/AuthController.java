package bg.softuni.battleships.controller;

import bg.softuni.battleships.model.dto.UserRegistrationDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

	@ModelAttribute("registrationDTO")
	public UserRegistrationDTO initRegistration() {
		return new UserRegistrationDTO();
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid UserRegistrationDTO registrationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO", bindingResult);

			return "redirect:/register";
		}

		return "redirect:/login";
	}
}