package bg.softuni.battleships.service;

import bg.softuni.battleships.model.dto.CreateShipDTO;
import bg.softuni.battleships.model.entity.CategoryEntity;
import bg.softuni.battleships.model.entity.ShipEntity;
import bg.softuni.battleships.model.entity.UserEntity;
import bg.softuni.battleships.model.enums.ShipType;
import bg.softuni.battleships.repository.CategoryRepository;
import bg.softuni.battleships.repository.ShipRepository;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipService {

	private ShipRepository shipRepository;
	private CategoryRepository categoryRepository;
	private UserRepository userRepository;
	private LoggedUser loggedUser;

	public ShipService(
			ShipRepository shipRepository,
			CategoryRepository categoryRepository,
			UserRepository userRepository,
			LoggedUser loggedUser) {
		this.shipRepository = shipRepository;
		this.categoryRepository = categoryRepository;
		this.userRepository = userRepository;
		this.loggedUser = loggedUser;
	}

	public boolean create(CreateShipDTO createShipDTO) {
		Optional<ShipEntity> byName = this.shipRepository.findByName(createShipDTO.getName());
		if (byName.isPresent()) {
			return false;
		}

		ShipType type = switch (createShipDTO.getCategory()) {
			case 0 -> ShipType.BATTLE;
			case 1 -> ShipType.CARGO;
			case 2 -> ShipType.PATROL;
			default -> ShipType.BATTLE;
		};
		CategoryEntity category = this.categoryRepository.findByName(type);
		ShipEntity ship = new ShipEntity();
		Optional<UserEntity> owner = this.userRepository.findById(this.loggedUser.getId());
		ship.setName(createShipDTO.getName())
				.setPower(createShipDTO.getPower())
				.setHealth(createShipDTO.getHealth())
				.setCategory(category)
				.setCreated(createShipDTO.getCreated())
				.setOwner(owner.get());
		this.shipRepository.save(ship);
		return true;
	}
}