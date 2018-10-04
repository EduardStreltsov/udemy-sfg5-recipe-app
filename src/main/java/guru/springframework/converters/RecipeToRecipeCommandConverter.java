package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommandConverter  implements Converter<Recipe, RecipeCommand> {
	
	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null) return null;
		
		final RecipeCommand target = new RecipeCommand();
		target.setId(source.getId());
		target.setDescription(source.getDescription());
		return target;
	}
}
