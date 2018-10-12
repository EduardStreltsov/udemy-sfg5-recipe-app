package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {
	
	@Mock
	RecipeService recipeService;
	RecipeController controller;
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void getRecipeTest() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		when(recipeService.getById(anyLong())).thenReturn(recipe);
		
		mockMvc.perform(get("/recipe/1/view"))
				.andExpect(status().isOk())
				.andExpect(view().name("/recipe/view"))
				.andExpect(model().attributeExists("recipe"));
	}
	
	@Test
	public void viewByIdTest() throws Exception {
		Recipe recipe = new Recipe();
		long id = 1L;
		recipe.setId(id);
		
		when(recipeService.getById(anyLong())).thenReturn(recipe);
		
		mockMvc.perform(get("/recipe/view/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("/recipe/view"))
				.andExpect(model().attributeExists("recipe"));
	}
	
	@Test
	public void postNewRecipeFormTest() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);
		
		when(recipeService.saveCommand(any())).thenReturn(command);
		
		mockMvc.perform(post("/recipe")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "")
				.param("description", "some string"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/view/2"));
	}
}