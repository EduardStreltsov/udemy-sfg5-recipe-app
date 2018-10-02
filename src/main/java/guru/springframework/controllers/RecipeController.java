package guru.springframework.controllers;

import guru.springframework.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
@AllArgsConstructor
public class RecipeController {
	
	private final RecipeService recipeService;
	
	@RequestMapping("/show/{id}")
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.getById(new Long(id)));
		return "show";
	}
	
	@RequestMapping("/new")
	public String addNewRecipe(Model model) {
		//		model.addAttribute("recipe", new RecipeCommand());
		return "form";
	}
	
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
	
	}
}