package bg.softuni.battleships.controller;

import bg.softuni.battleships.model.dto.CreateShipDTO;
import bg.softuni.battleships.service.AuthService;
import bg.softuni.battleships.service.ShipService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipController {

	public static final String SHIP_ADD = "ship-add";
	private final ShipService shipService;
	private final AuthService authService;

	public ShipController(ShipService shipService, AuthService authService) {
		this.shipService = shipService;
		this.authService = authService;
	}

	@ModelAttribute("createShipDTO")
	public CreateShipDTO initCreateShipDTO() {
		return new CreateShipDTO();
	}

	@GetMapping("/ships")
	public String ships() {
		if (!this.authService.isLogged()) {
			return "redirect:/";
		}

		return SHIP_ADD;
	}

	@PostMapping("/ships")
	public String ships(@Valid CreateShipDTO createShipDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (!this.authService.isLogged()) {
			return "redirect:/";
		}

		if (bindingResult.hasErrors() || !this.shipService.create(createShipDTO)) {
			redirectAttributes.addFlashAttribute("createShipDTO", createShipDTO);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createShipDTO", bindingResult);

			return "redirect:/ships";
		}
		return "redirect:/home";
	}
}