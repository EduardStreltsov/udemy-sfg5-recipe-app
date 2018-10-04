package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipeConverter  implements Converter<RecipeCommand, Recipe> {
	
	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {
		if (source == null) return null;
		
		final Recipe target = new Recipe();
		target.setId(source.getId());
		target.setDescription(source.getDescription());
		return target;
	}
}
