package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.domain.Category;
import org.test.repository.CategoryRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
@SuppressWarnings(ServiceStr.UNUSED)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ReloadableResourceBundleMessageSource resourceBundle;

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategory(Long id) {
        if(id == null) {
            throw new IllegalArgumentException(this.resourceBundle.getMessage("org.test.demo.message.category.id.null",
                   null, Locale.getDefault()));
        }
        Category category = categoryRepository.findOne(id);
        if(category == null) {
            throw new ResourceNotFoundException(this.resourceBundle.getMessage("org.test.demo.message.category.not.found",
                    new Object[]{id}, Locale.getDefault()));
        }
        return category;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAllOrderByName();
    }

    public Category createCategory(@NotNull @Valid Category category) {
        if(categoryRepository.findByName(category.getName()) != null) {
            throw new DuplicateKeyException(this.resourceBundle.getMessage("org.test.demo.message.category.duplicate.key",
                    new Object[]{category.getName()}, Locale.getDefault()));
        }
        return categoryRepository.save(category);
    }

    public void updateCategory(@NotNull @Valid Category category) {
        Category oldCategory = this.getCategory(category.getId());
        if(!oldCategory.getName().equals(category.getName()) && categoryRepository.findByName(category.getName()) != null) {
            throw new DuplicateKeyException(this.resourceBundle.getMessage("org.test.demo.message.category.duplicate.key",
                    new Object[]{category.getName()}, Locale.getDefault()));
        }
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        Category category = this.getCategory(id);
        if(category.getSubcategories().size() > 0) {
            throw new DataIntegrityViolationException(this.resourceBundle.getMessage("org.test.demo.message.category.data.integrity",
                    new Object[]{id}, Locale.getDefault()));
        }
        categoryRepository.delete(category);
    }
}
