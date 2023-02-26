package bg.softuni.battleships.repository;

import bg.softuni.battleships.model.entity.CategoryEntity;
import bg.softuni.battleships.model.enums.ShipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	CategoryEntity findByName(ShipType type);
}
