package bg.softuni.battleships.model.dto;

import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class StartBattleDTO {

	@Positive
	private int attackerId;

	@Positive
	private int defenderId;
}