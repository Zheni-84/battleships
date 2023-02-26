package bg.softuni.battleships.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * A DTO for the {@link bg.softuni.battleships.model.entity.UserEntity} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserLoginDTO implements Serializable {

	@Size(min = 3, max = 10)
	@NotBlank
	private String username;

	@Size(min = 3)
	@NotBlank
	private String password;
}