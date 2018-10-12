package guru.springframework.service;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {
	
	Set<Recipe> getAll();
	
	Recipe getById(Long id);
	
	RecipeCommand getCommandById(Long id);
	
	RecipeCommand saveCommand(RecipeCommand recipeCommand);
}
