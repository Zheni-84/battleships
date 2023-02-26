package bg.softuni.battleships.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String indexLoggedOut() {
		return "index";
	}


	@GetMapping("/home")
	public String indexLoggedIn() {
		return "home";
	}
}
