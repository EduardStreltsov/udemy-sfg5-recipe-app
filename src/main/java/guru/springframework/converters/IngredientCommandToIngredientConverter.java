package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredientConverter  implements Converter<IngredientCommand, Ingredient> {
	
	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand source) {
		if (source == null) return null;
		
		final Ingredient target = new Ingredient();
		target.setId(source.getId());
		target.setDescription(source.getDescription());
		return target;
	}
}
