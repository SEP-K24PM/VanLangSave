package com.vls.managementservice.repository;

import com.vls.managementservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query(value = "SELECT\n" +
            "    Category.id, Category.category_name,\n" +
            "    (SELECT COUNT(*) FROM thing t WHERE t.category_id = Category.id) \n" +
            "  \n" +
            "FROM\n" +
            "    Category ", nativeQuery = true)
    List<Category> countAllCategory();
}
