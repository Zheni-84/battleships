package bg.softuni.battleships.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
public class CreateShipDTO implements Serializable {

	@NotBlank
	@Size(min = 2, max = 10)
	private String name;

	@Positive
	private long health;

	@Positive
	private long power;

	@PastOrPresent
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate created;

	@Positive
	private int category = -1;
}