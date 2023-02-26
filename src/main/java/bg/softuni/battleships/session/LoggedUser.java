package bg.softuni.battleships.session;

import bg.softuni.battleships.model.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
public class LoggedUser {

	private Long id;

	private String fullName;

	public void login(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.fullName = userEntity.getFullName();
	}

	public void logout() {
		this.id = null;
		this.fullName = null;
	}

	public boolean isLogged() {
		return this.getId() != null;
	}
}