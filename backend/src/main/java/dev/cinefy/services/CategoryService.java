package dev.cinefy.services;

import dev.cinefy.entities.Category;
import dev.cinefy.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService{
    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElse(null);
    }

    public void deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
    }
}
