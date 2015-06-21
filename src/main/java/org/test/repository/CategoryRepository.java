package org.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.test.domain.Category;
import org.test.domain.Subcategory;

import java.util.List;

@SuppressWarnings("unused")
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c order by c.name")
    List<Category> findAllOrderByName();

    Category findByName(String name);

}
