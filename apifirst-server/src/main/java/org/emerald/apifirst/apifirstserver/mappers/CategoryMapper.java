package org.emerald.apifirst.apifirstserver.mappers;

import org.emerald.apifirst.apifirstserver.domain.Category;
import org.emerald.apifirst.model.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper {
    CategoryDto categoryToCategoryDto(Category category);

    @Mapping(target = "products", ignore = true)
    Category categoryDtoToCategory(CategoryDto categoryDto);
}