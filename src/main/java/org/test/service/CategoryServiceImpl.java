package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.domain.Category;
import org.test.repository.CategoryRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@SuppressWarnings(ServiceStr.UNUSED)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategory(@NotNull Long id) {
        Category category = categoryRepository.findOne(id);
        if(category == null) throw new ResourceNotFoundException();
        return category;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAllOrderByName();
    }

    public Category createCategory(@NotNull @Valid Category category) {
        if(categoryRepository.findByName(category.getName()) != null) throw new DuplicateKeyException("name");
        return categoryRepository.save(category);
    }

    public void updateCategory(@NotNull @Valid Category category) {

    }

    public void deleteCategory(@NotNull Long id) {

    }
}