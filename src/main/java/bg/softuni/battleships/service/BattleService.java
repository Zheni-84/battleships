package bg.softuni.battleships.service;

import bg.softuni.battleships.model.dto.StartBattleDTO;
import bg.softuni.battleships.model.entity.ShipEntity;
import bg.softuni.battleships.repository.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BattleService {

	private final ShipRepository shipRepository;

	public BattleService(ShipRepository shipRepository) {
		this.shipRepository = shipRepository;
	}

	public void attack(StartBattleDTO attackData) {
		Optional<ShipEntity> attackerOpt = this.shipRepository.findById((long) attackData.getAttackerId());
		Optional<ShipEntity> defenderOpt = this.shipRepository.findById((long) attackData.getDefenderId());
		if (attackerOpt.isEmpty() || defenderOpt.isEmpty()) {
			throw new NoSuchElementException();
		}
		ShipEntity attacker = attackerOpt.get();
		ShipEntity defender = defenderOpt.get();

		long newDefenderHealth = defender.getHealth() - attacker.getPower();

		if (newDefenderHealth <= 0) {
			this.shipRepository.delete(defender);
		} else {
			defender.setHealth(newDefenderHealth);
			this.shipRepository.save(defender);
		}
	}
}