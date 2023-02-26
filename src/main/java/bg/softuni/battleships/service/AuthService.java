package bg.softuni.battleships.service;

import bg.softuni.battleships.model.dto.UserLoginDTO;
import bg.softuni.battleships.model.dto.UserRegistrationDTO;
import bg.softuni.battleships.model.entity.UserEntity;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

	private final UserRepository userRepository;
	private final LoggedUser userSession;

	public AuthService(UserRepository userRepository, LoggedUser userSession) {
		this.userRepository = userRepository;
		this.userSession = userSession;
	}

	public boolean register(UserRegistrationDTO userRegistrationDTO) {
		if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
			return false;
		}
		Optional<UserEntity> byEmail = this.userRepository.findByEmail(userRegistrationDTO.getEmail());
		if (byEmail.isPresent()) {
			return false;
		}

		Optional<UserEntity> byUsername = this.userRepository.findByUsername(userRegistrationDTO.getUsername());
		if (byUsername.isPresent()) {
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

	public boolean login(UserLoginDTO loginDTO) {
		Optional<UserEntity> user = this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
		if (user.isEmpty()) {
			return false;
		}
		//actual login
		this.userSession.login(user.get());

		return true;
	}

	public boolean isLogged() {
		return this.userSession.isLogged();
	}

	public void logout() {
		this.userSession.logout();
	}

	public long getLoggedUserId() {
		return this.userSession.getId();
	}
}