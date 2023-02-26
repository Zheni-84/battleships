package bg.softuni.battleships.controller;

import bg.softuni.battleships.model.dto.StartBattleDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BattleController {

	@PostMapping("/battle")
	public String battle(@Valid StartBattleDTO startBattleDTO) {

		return "redirect:/home";
	}
}