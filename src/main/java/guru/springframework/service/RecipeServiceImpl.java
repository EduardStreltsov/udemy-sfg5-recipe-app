package guru.springframework.service;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipeConverter;
import guru.springframework.converters.RecipeToRecipeCommandConverter;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {
	
	private final RecipeRepository recipeRepository;
	private final RecipeToRecipeCommandConverter recipeToRecipeCommandConverter;
	private final RecipeCommandToRecipeConverter recipeCommandToRecipeConverter;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository,
	                         RecipeToRecipeCommandConverter recipeToRecipeCommandConverter,
	                         RecipeCommandToRecipeConverter recipeCommandToRecipeConverter) {
		this.recipeRepository = recipeRepository;
		this.recipeToRecipeCommandConverter = recipeToRecipeCommandConverter;
		this.recipeCommandToRecipeConverter = recipeCommandToRecipeConverter;
	}
	
	@Override
	public Set<Recipe> getRecipes() {
		log.info("--- logging RecipeServiceImpl.getRecipes");
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}
	
	@Override
	public Recipe getById(Long id) {
		return recipeRepository.findById(id)
				       .orElseThrow(() -> new RuntimeException("There is no recipe with id " + id));
	}
	
	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		Recipe detachedRecipe = recipeCommandToRecipeConverter.convert(recipeCommand);
		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		log.debug("Saved RecipeId: " + savedRecipe.getId());
		return recipeToRecipeCommandConverter.convert(savedRecipe);
	}
}
