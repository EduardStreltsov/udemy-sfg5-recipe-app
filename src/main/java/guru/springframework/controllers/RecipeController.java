package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recipe")
@AllArgsConstructor
@Log4j2
public class RecipeController {
	
	private final RecipeService recipeService;
	
	@RequestMapping("/view/{id}")
	public String viewById(@PathVariable String id, Model model) {
		log.info("entered endpoint viewById");
		Recipe recipe = recipeService.getById(new Long(id));
		model.addAttribute("recipe", recipe);
		return "recipe/view";
	}
	
	@RequestMapping("/new")
	public String addNew(Model model) {
		log.info("entered endpoint addNew");
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/edit";
	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.getCommandById(Long.valueOf(id)));
		return "recipe/edit";
	}
	
	@PostMapping
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		log.info("entered endpoint saveOrUpdate");
		RecipeCommand savedCommand = recipeService.saveCommand(command);
		return "redirect:/recipe/view/" + savedCommand.getId();
	}
	
	@DeleteMapping
	public String delete(){
		return "";
	}
}