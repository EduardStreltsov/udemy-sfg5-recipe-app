package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommandConverter  implements Converter<Ingredient, IngredientCommand> {
	
	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient source) {
		if (source == null) return null;
		
		final IngredientCommand target = new IngredientCommand();
		target.setId(source.getId());
		target.setDescription(source.getDescription());
		return target;
	}
}
