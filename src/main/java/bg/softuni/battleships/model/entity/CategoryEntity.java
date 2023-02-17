package bg.softuni.battleships.model.entity;

import bg.softuni.battleships.model.enums.ShipType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

	@Column(nullable = false, unique = true)
	@Enumerated(EnumType.ORDINAL)
	private ShipType name;

	@Column(columnDefinition = "TEXT")
	private String description;

	public CategoryEntity(ShipType name) {
		this.name = name;
	}
}