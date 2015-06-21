package org.test.controller;

import org.test.domain.Subcategory;

@SuppressWarnings( ApiController.UNUSED)
public class SubcategoryBean {

    public Long id;
    public String name;
    public String link;
    public RecordBean record;
    public Category category = new Category();

    public SubcategoryBean() {}

    public SubcategoryBean(Subcategory subcategory) {
        this.id = subcategory.getId();
        this.name = subcategory.getName();
        this.link = ApiController.SUBCATEGORIES_URL+"/"+this.id;
        this.record = new RecordBean(subcategory.getRecord());
        this.category.id = subcategory.getCategory().getId();
        this.category.name = subcategory.getCategory().getName();
        this.category.link = ApiController.CATEGORIES_URL+"/"+this.category.id;
    }

    public class Category {
        public Long id;
        public String name;
        public String link;
    }

}
