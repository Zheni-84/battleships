package bg.softuni.battleships.repository;

import bg.softuni.battleships.model.entity.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {

	Optional<ShipEntity> findByName(String name);

	List<ShipEntity> findAllByOwnerId(Long ownerId);

	List<ShipEntity> findAllByOwnerIdNot(Long ownerId);

	List<ShipEntity> findByOrderByHealthAscNameDescPowerAsc();
}