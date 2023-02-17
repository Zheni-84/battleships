package bg.softuni.battleships.service;

import bg.softuni.battleships.model.dto.UserRegistrationDTO;
import bg.softuni.battleships.model.entity.UserEntity;
import bg.softuni.battleships.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

	private UserRepository userRepository;

	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public boolean register(UserRegistrationDTO userRegistrationDTO) {
		if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
			return false;
		}
		Optional<UserEntity> byEmail = this.userRepository.findByEmail(userRegistrationDTO.getEmail());
		if (byEmail.isPresent()){
			return false;
		}

		Optional<UserEntity> byUsername = this.userRepository.findByUsername(userRegistrationDTO.getUsername());
		if (byUsername.isPresent()){
			return false;
		}

		UserEntity user = UserEntity.builder()
				.username(userRegistrationDTO.getUsername())
				.email(userRegistrationDTO.getEmail())
				.fullName(userRegistrationDTO.getFullName())
				.password(userRegistrationDTO.getPassword())
				.build();
		userRepository.save(user);

		return true;
	}
}
