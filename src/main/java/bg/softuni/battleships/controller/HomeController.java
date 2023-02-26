package bg.softuni.battleships.controller;

import bg.softuni.battleships.model.dto.ShipEntityDTO;
import bg.softuni.battleships.model.dto.StartBattleDTO;
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
	private final LoggedUser loggedUser;

	public HomeController(ShipService shipService, LoggedUser loggedUser) {
		this.shipService = shipService;
		this.loggedUser = loggedUser;
	}

	@ModelAttribute("startBattleDTO")
	public StartBattleDTO initBattleForm(){
		return new StartBattleDTO();
	}


	@GetMapping("/")
	public String indexLoggedOut() {
		return "index";
	}

	@GetMapping("/home")
	public String indexLoggedIn(Model model) {
		Long loggedUserId = this.loggedUser.getId();
		List<ShipEntityDTO> ownShips = this.shipService.getShipsOwnedBy(loggedUserId);
		List<ShipEntityDTO> enemyShips = this.shipService.getShipsNotOwnedBy(loggedUserId);

		model.addAttribute("ownShips", ownShips);
		model.addAttribute("enemyShips", enemyShips);

		return "home";
	}
}