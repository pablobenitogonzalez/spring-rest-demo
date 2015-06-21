package org.test.service;

import org.test.domain.Subcategory;

import java.util.List;

@SuppressWarnings("unused")
public interface SubcategoryService {
    public Subcategory getSubcategory(Long id);
    public List<Subcategory> getAllSubcategories();
    public Subcategory createSubcategory(Subcategory subcategory);
    public void updateSubcategory(Subcategory subcategory);
    public void deleteSubcategory(Long id);
}
