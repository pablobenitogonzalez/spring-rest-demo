package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.domain.Subcategory;
import org.test.repository.SubcategoryRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
@SuppressWarnings(ServiceStr.UNUSED)
public class SubcategoryServiceImpl implements SubcategoryService {

    @Autowired
    private ReloadableResourceBundleMessageSource resourceBundle;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public Subcategory getSubcategory(Long id) {
        if(id == null) {
            throw new IllegalArgumentException(this.resourceBundle.getMessage("org.test.demo.message.subcategory.id.null",
                    null, Locale.getDefault()));
        }
        Subcategory subcategory = subcategoryRepository.findOne(id);
        if(subcategory == null) {
            throw new ResourceNotFoundException(this.resourceBundle.getMessage("org.test.demo.message.subcategory.not.found",
                    new Object[]{id}, Locale.getDefault()));
        }
        return subcategory;
    }

    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAllOrderByName();
    }

    public Subcategory createSubcategory(@NotNull @Valid Subcategory subcategory) {
        if(subcategoryRepository.findByNameAndCategory_Id(subcategory.getName(), subcategory.getCategory().getId()) != null)
            throw new DuplicateKeyException(this.resourceBundle.getMessage("org.test.demo.message.subcategory.duplicate.key",
                    new Object[]{subcategory.getName(), subcategory.getCategory().getId()}, Locale.getDefault()));
        return subcategoryRepository.save(subcategory);
    }

    public void updateSubcategory(@NotNull @Valid Subcategory subcategory) {
        Subcategory oldSubcategory = this.getSubcategory(subcategory.getId());
        if(subcategory.getCategory() == null) {
            subcategory.setCategory(oldSubcategory.getCategory());
        }
        if(!(oldSubcategory.getName().equals(subcategory.getName()) && oldSubcategory.getId().equals(subcategory.getId()))
                && subcategoryRepository.findByNameAndCategory_Id(subcategory.getName(), subcategory.getCategory().getId()) != null)
            throw new DuplicateKeyException(this.resourceBundle.getMessage("org.test.demo.message.subcategory.duplicate.key",
                    new Object[]{subcategory.getName(), subcategory.getCategory().getId()}, Locale.getDefault()));
        subcategoryRepository.save(subcategory);
    }

    public void deleteSubcategory(Long id) {
        Subcategory subcategory = this.getSubcategory(id);
        subcategoryRepository.delete(subcategory);
    }

}
