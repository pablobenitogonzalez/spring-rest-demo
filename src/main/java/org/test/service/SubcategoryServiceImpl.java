package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.domain.Subcategory;
import org.test.repository.SubcategoryRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@SuppressWarnings(ServiceStr.UNUSED)
public class SubcategoryServiceImpl implements SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public Subcategory getSubcategory(Long id) {
        if(id == null) throw new IllegalArgumentException("subcategory id null");
        Subcategory subcategory = subcategoryRepository.findOne(id);
        if(subcategory == null) throw new ResourceNotFoundException();
        return subcategory;
    }

    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAllOrderByName();
    }

    public Subcategory createSubcategory(@NotNull @Valid Subcategory subcategory) {
        if(subcategoryRepository.findByNameAndCategory_Id(subcategory.getName(), subcategory.getCategory().getId()) != null)
            throw new DuplicateKeyException("name");
        return subcategoryRepository.save(subcategory);
    }

    public void updateSubcategory(@NotNull @Valid Subcategory subcategory) {
        Subcategory oldSubcategory = this.getSubcategory(subcategory.getId());
        if(subcategory.getCategory() == null) {
            subcategory.setCategory(oldSubcategory.getCategory());
        }
        if(!(oldSubcategory.getName().equals(subcategory.getName()) && oldSubcategory.getId().equals(subcategory.getId()))
                && subcategoryRepository.findByNameAndCategory_Id(subcategory.getName(), subcategory.getCategory().getId()) != null)
            throw new DuplicateKeyException("name");
        subcategoryRepository.save(subcategory);
    }

    public void deleteSubcategory(Long id) {
        if(id == null) throw new IllegalArgumentException("subcategory id null");
        Subcategory subcategory = this.getSubcategory(id);
        subcategoryRepository.delete(subcategory);
    }

}
