package guru.springframework.service;

import guru.springframework.converters.RecipeCommandToRecipeConverter;
import guru.springframework.converters.RecipeToRecipeCommandConverter;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeService;
	@Mock
	RecipeRepository recipeRepository;
	@Mock
	RecipeToRecipeCommandConverter recipeToRecipeCommandConverter;
	@Mock
	RecipeCommandToRecipeConverter recipeCommandToRecipeConverter;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommandConverter, recipeCommandToRecipeConverter);
	}
	
	@Test
	public void getRecipesTest() {
		
		Recipe recipe = new Recipe();
		HashSet recipeData = new HashSet();
		recipeData.add(recipe);
		
		when(recipeService.getAll()).thenReturn(recipeData);
		
		Set<Recipe> recipeSet = recipeService.getAll();
		assertEquals(recipeSet.size(), 1);
		verify(recipeRepository, times(1)).findAll();
	}
	
	@Test
	public void getByIdTest() {
		Recipe recipe = new Recipe();
		long id = 1L;
		recipe.setId(id);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		
		Recipe recipeReturned = recipeService.getById(id);
		
		assertNotNull("Null recipe returned", recipeReturned);
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
	}
	
	
}