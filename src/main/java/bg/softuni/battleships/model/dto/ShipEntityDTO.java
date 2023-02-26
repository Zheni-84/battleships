package bg.softuni.battleships.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link bg.softuni.battleships.model.entity.ShipEntity} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ShipEntityDTO implements Serializable {

	private long id;
	private String name;
	private long health;
	private long power;
	private LocalDate created;
}