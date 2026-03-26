package dev.cinefy.controllers;

import dev.cinefy.entities.Category;
import dev.cinefy.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController{
    private final CategoryService categoryService;

    @GetMapping("/health")
    public String healthCheck(){
        return "Hello World!";
    }

    @PostMapping("/create")
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

}
