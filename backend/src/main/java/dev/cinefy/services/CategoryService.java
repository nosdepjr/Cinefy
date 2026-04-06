package dev.cinefy.services;

import dev.cinefy.controllers.request.CategoryRequest;
import dev.cinefy.controllers.response.CategoryResponse;
import dev.cinefy.entities.Category;
import dev.cinefy.mappers.CategoryMapper;
import dev.cinefy.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CategoryRequest request){
        return CategoryMapper.toCategoryResponse(categoryRepository.save(CategoryMapper.toCategory((request))));
    }

    public List<CategoryResponse> findAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    public CategoryResponse findCategoryById(Long id){
        Category category = categoryRepository
                .findById(id)
                .orElse(null);
        return category != null? CategoryMapper.toCategoryResponse(category):null;

    }

    public Category findEntityById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            return null;
        }

        if (request.name() != null) {
            category.setName(request.name());
        }

        Category updatedCategory = categoryRepository.save(category);

        return CategoryMapper.toCategoryResponse(updatedCategory);
    }

    public void deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
    }
}
