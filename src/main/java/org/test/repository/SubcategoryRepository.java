package org.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.test.domain.Subcategory;

import java.util.List;

@SuppressWarnings(RepositoryStr.UNUSED)
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    @Query("select s from Subcategory s order by s.name")
    List<Subcategory> findAllOrderByName();

    Subcategory findByName(String name);

}
