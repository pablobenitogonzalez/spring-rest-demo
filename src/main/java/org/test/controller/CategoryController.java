package org.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.test.domain.Category;
import org.test.service.CategoryService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(RestPaths.PATH_CATEGORIES)
@SuppressWarnings(RestPaths.UNUSED)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryBean getCategory(@PathVariable Long id) throws Exception {
        Category category = categoryService.getCategory(id);
        return new CategoryBean(category);
    }

    @RequestMapping(method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryBean> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryBean> categoryBeans = new ArrayList<>();
        for(Category category : categories) {
            categoryBeans.add(new CategoryBean(category));
        }
        return categoryBeans;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryBean> createCategory(@RequestBody CategoryBean categoryBean, UriComponentsBuilder uriComponentsBuilder) {
        Category category = categoryService.createCategory(new Category(categoryBean.name));
        CategoryBean categoryBeanCreated = new CategoryBean(category);
        HttpHeaders httpHeaders = RestUtilities.getHttpHeaders(uriComponentsBuilder, RestPaths.PATH_CATEGORIES, String.valueOf(categoryBeanCreated.id));
        return new ResponseEntity<>(categoryBeanCreated, httpHeaders, HttpStatus.CREATED);
    }

}
