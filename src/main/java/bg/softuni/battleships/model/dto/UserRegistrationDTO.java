package bg.softuni.battleships.model.dto;

import jakarta.validation.constraints.Email;
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
public class UserRegistrationDTO implements Serializable {

	@Size(min = 3, max = 10)
	@NotBlank
	private String username;

	@Size(min = 5, max = 20)
	@NotBlank
	private String fullName;

	@Email
	@NotBlank
	private String email;

	@Size(min = 3)
	@NotBlank
	private String password;

	@Size(min = 3)
	@NotBlank
	private String confirmPassword;
}