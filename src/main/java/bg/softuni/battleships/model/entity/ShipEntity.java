package bg.softuni.battleships.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ships")
@Accessors(chain = true)
public class ShipEntity extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String name;

	private long health;

	private long power;

	@Column(nullable = false)
	private LocalDate created;

	@ManyToOne
	private CategoryEntity category;

	@ManyToOne
	private UserEntity owner;
}