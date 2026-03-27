package dev.cinefy.mappers;

import dev.cinefy.controllers.request.CategoryRequest;
import dev.cinefy.controllers.response.CategoryResponse;
import dev.cinefy.entities.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper{
    public static Category toCategory(CategoryRequest categoryRequest){
        return Category
                .builder()
                .name(categoryRequest.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
