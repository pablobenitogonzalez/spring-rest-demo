package org.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.test.domain.Subcategory;
import org.test.service.SubcategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subcategories")
@SuppressWarnings(RestPaths.UNUSED)
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @RequestMapping(method = RequestMethod.GET)
    public List<SubcategoryBean> getAllCategories() {
        List<Subcategory> subcategories = subcategoryService.getAllSubcategories();
        List<SubcategoryBean> subcategoryBeans = new ArrayList<>();
        for(Subcategory subcategory : subcategories) {
            subcategoryBeans.add(new SubcategoryBean(subcategory));
        }
        return subcategoryBeans;
    }
}
