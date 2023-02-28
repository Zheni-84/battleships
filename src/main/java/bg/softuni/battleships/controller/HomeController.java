package bg.softuni.battleships.controller;

import bg.softuni.battleships.model.dto.ShipEntityDTO;
import bg.softuni.battleships.model.dto.StartBattleDTO;
import bg.softuni.battleships.service.AuthService;
import bg.softuni.battleships.service.ShipService;
import bg.softuni.battleships.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

	private final ShipService shipService;
	private final AuthService authService;

	public HomeController(ShipService shipService, AuthService authService) {
		this.shipService = shipService;
		this.authService = authService;
	}

	@ModelAttribute("startBattleDTO")
	public StartBattleDTO initBattleForm(){
		return new StartBattleDTO();
	}


	@GetMapping("/")
	public String indexLoggedOut() {
		if(this.authService.isLogged()) {
			return "redirect:/home";
		}

		return "index";
	}

	@GetMapping("/home")
	public String indexLoggedIn(Model model) {
		if(!this.authService.isLogged()) {
			return "redirect:/";
		}
		Long loggedUserId = this.authService.getLoggedUserId();
		List<ShipEntityDTO> ownShips = this.shipService.getShipsOwnedBy(loggedUserId);
		List<ShipEntityDTO> enemyShips = this.shipService.getShipsNotOwnedBy(loggedUserId);
		List<ShipEntityDTO> sortedShips = this.shipService.getAllSorted();

		model.addAttribute("ownShips", ownShips);
		model.addAttribute("enemyShips", enemyShips);
		model.addAttribute("sortedShips", sortedShips);

		return "home";
	}
}