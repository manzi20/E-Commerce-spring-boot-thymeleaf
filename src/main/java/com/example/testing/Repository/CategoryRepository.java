package com.example.testing.Repository;

import com.example.testing.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {


//    @Query("SELECT c from Category c where  c.enabled =true order by c.CategoryName ASC ")
//
// public List<Category> findAllEnabled();

}
