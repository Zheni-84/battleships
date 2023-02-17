package bg.softuni.battleships;

import bg.softuni.battleships.model.entity.CategoryEntity;
import bg.softuni.battleships.model.enums.ShipType;
import bg.softuni.battleships.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategorySeeder implements CommandLineRunner {

	private final CategoryRepository categoryRepository;

	public CategorySeeder(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		if (this.categoryRepository.count() == 0) {
			List<CategoryEntity> categories = Arrays.stream(ShipType.values())
					.map(CategoryEntity::new)
					.collect(Collectors.toList());
			this.categoryRepository.saveAll(categories);
		}

	}
}
