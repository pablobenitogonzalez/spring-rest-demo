package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.domain.Subcategory;
import org.test.repository.SubcategoryRepository;

import java.util.List;

@Service
@Transactional
public class SubcategoryServiceImpl implements SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Override
    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAllOrderByName();
    }
}
