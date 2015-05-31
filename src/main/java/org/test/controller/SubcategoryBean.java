package org.test.controller;

import org.test.domain.Subcategory;

@SuppressWarnings(RestPaths.UNUSED)
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
        this.link = RestPaths.PATH_SUBCATEGORIES+"/"+this.id;
        this.record = new RecordBean(subcategory.getRecord());
        this.category.id = subcategory.getCategory().getId();
        this.category.name = subcategory.getCategory().getName();
        this.category.link = RestPaths.PATH_CATEGORIES+"/"+this.category.id;
    }

    public class Category {
        public Long id;
        public String name;
        public String link;
    }

}
