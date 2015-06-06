package org.test.service;

import org.test.domain.Category;

import java.util.List;

@SuppressWarnings(ServiceStr.UNUSED)
public interface CategoryService {
    public Category getCategory(Long id);
    public List<Category> getAllCategories();
    public Category createCategory(Category category);
    public void updateCategory(Category category);
    public void deleteCategory(Long id);
}
