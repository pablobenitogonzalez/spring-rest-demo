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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( ApiController.CATEGORIES_URL)
@SuppressWarnings( ApiController.UNUSED)
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
        HttpHeaders httpHeaders = ApiHeaders.getCreatedResourceHttpHeaders(uriComponentsBuilder, ApiController.CATEGORIES_URL, String.valueOf(categoryBeanCreated.id));
        return new ResponseEntity<>(categoryBeanCreated, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@RequestBody CategoryBean categoryBean) {
        Category category = new Category();
        category.setId(categoryBean.id);
        category.setName(categoryBean.name);
        categoryService.updateCategory(category);
    }

    @RequestMapping(value = "/{id}",
                    method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

}
