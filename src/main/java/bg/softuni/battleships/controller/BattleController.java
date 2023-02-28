package bg.softuni.battleships.controller;

import bg.softuni.battleships.model.dto.StartBattleDTO;
import bg.softuni.battleships.service.AuthService;
import bg.softuni.battleships.service.BattleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BattleController {

	public static final String REDIRECT_HOME = "redirect:/home";
	public static final String REDIRECT = "redirect:/";
	private final BattleService battleService;
	private final AuthService authService;

	public BattleController(BattleService battleService, AuthService authService) {
		this.battleService = battleService;
		this.authService = authService;
	}

	@GetMapping("/battle")
	public String ships() {
		return REDIRECT;
	}

	@PostMapping("/battle")
	public String battle(@Valid StartBattleDTO startBattleDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (!this.authService.isLogged()) {
			return REDIRECT;
		}

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("startBattleDTO", startBattleDTO);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.startBattleDTO", bindingResult);

			return REDIRECT_HOME;
		}
		this.battleService.attack(startBattleDTO);

		return REDIRECT_HOME;
	}
}