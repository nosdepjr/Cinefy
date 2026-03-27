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

    public CategoryResponse createCategory(CategoryRequest categoryRequest){
        return CategoryMapper.toCategoryResponse(categoryRepository.save(CategoryMapper.toCategory((categoryRequest))));
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

    public void deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
    }
}
