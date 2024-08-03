package org.emerald.apifirst.apifirstserver.services;

import org.emerald.apifirst.model.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> listCategories();
}
