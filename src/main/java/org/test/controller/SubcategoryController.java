package org.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.test.domain.Category;
import org.test.domain.Subcategory;
import org.test.service.CategoryService;
import org.test.service.SubcategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( ApiController.SUBCATEGORIES_URL)
@SuppressWarnings( ApiController.UNUSED)
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SubcategoryBean getSubcategory(@PathVariable Long id) throws Exception {
        Subcategory subcategory = subcategoryService.getSubcategory(id);
        return new SubcategoryBean(subcategory);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SubcategoryBean> getAllCategories() {
        List<Subcategory> subcategories = subcategoryService.getAllSubcategories();
        List<SubcategoryBean> subcategoryBeans = new ArrayList<>();
        for(Subcategory subcategory : subcategories) {
            subcategoryBeans.add(new SubcategoryBean(subcategory));
        }
        return subcategoryBeans;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubcategoryBean> createSubcategory(@RequestBody SubcategoryBean subcategoryBean, UriComponentsBuilder uriComponentsBuilder) {
        Category category = categoryService.getCategory(subcategoryBean.category.id);
        Subcategory subcategory = subcategoryService.createSubcategory(new Subcategory(subcategoryBean.name, category));
        SubcategoryBean subcategoryBeanCreated = new SubcategoryBean(subcategory);
        HttpHeaders httpHeaders = ApiHeaders.getCreatedResourceHttpHeaders(uriComponentsBuilder, ApiController.SUBCATEGORIES_URL, String.valueOf(subcategoryBeanCreated.id));
        return new ResponseEntity<>(subcategoryBeanCreated, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,
                   consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSubcategory(@RequestBody SubcategoryBean subcategoryBean) {
        Subcategory subcategory = new Subcategory();
        subcategory.setId(subcategoryBean.id);
        subcategory.setName(subcategoryBean.name);
        if(subcategoryBean.category.id != null) {
            Category category = categoryService.getCategory(subcategoryBean.category.id);
            subcategory.setCategory(category);
        }
        subcategoryService.updateSubcategory(subcategory);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubcategory(@PathVariable Long id) {
        subcategoryService.deleteSubcategory(id);
    }

}
