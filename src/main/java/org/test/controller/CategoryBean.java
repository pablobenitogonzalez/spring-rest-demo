package org.test.controller;

import org.test.domain.Category;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings( ApiController.UNUSED)
public class CategoryBean {
    public Long id;
    public String name;
    public String link;
    public RecordBean record;
    public List<Subcategory> subcategories = new ArrayList<>();

    public CategoryBean() {}

    public CategoryBean(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.link = ApiController.CATEGORIES_URL+"/"+this.id;
        this.record = new RecordBean(category.getRecord());
        for(org.test.domain.Subcategory subcategory : category.getSubcategories()) {
            Subcategory subc = new Subcategory();
            subc.id = subcategory.getId();
            subc.name = subcategory.getName();
            subc.link = ApiController.SUBCATEGORIES_URL+"/"+subc.id;
            subcategories.add(subc);
        }
    }

    public class Subcategory {
        public Long id;
        public String name;
        public String link;
    }
}
