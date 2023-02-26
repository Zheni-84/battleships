package bg.softuni.battleships.service;

import bg.softuni.battleships.model.dto.CreateShipDTO;
import bg.softuni.battleships.model.dto.ShipEntityDTO;
import bg.softuni.battleships.model.entity.CategoryEntity;
import bg.softuni.battleships.model.entity.ShipEntity;
import bg.softuni.battleships.model.entity.UserEntity;
import bg.softuni.battleships.model.enums.ShipType;
import bg.softuni.battleships.repository.CategoryRepository;
import bg.softuni.battleships.repository.ShipRepository;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {

	private final ShipRepository shipRepository;
	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;
	private final LoggedUser loggedUser;

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

	public List<ShipEntityDTO> getShipsOwnedBy(Long ownerId) {
		return this.shipRepository.findAllByOwnerId(ownerId).stream()
				.map(ship -> new ShipEntityDTO().setId(ship.getId())
						.setName(ship.getName())
						.setHealth(ship.getHealth())
						.setPower(ship.getPower()))
				.collect(Collectors.toList());
	}

	public List<ShipEntityDTO> getShipsNotOwnedBy(Long ownerId) {
		return this.shipRepository.findAllByOwnerIdNot(ownerId).stream()
				.map(ship -> new ShipEntityDTO().setId(ship.getId())
						.setName(ship.getName())
						.setHealth(ship.getHealth())
						.setPower(ship.getPower()))
				.collect(Collectors.toList());
	}

	public List<ShipEntityDTO> getAllSorted() {
		return this.shipRepository.findByOrderByHealthAscNameDescPowerAsc().stream()
				.map(ship -> new ShipEntityDTO().setId(ship.getId())
						.setName(ship.getName())
						.setHealth(ship.getHealth())
						.setPower(ship.getPower()))
				.collect(Collectors.toList());
	}
}