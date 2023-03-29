package com.example.testing.Repository;

import com.example.testing.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    List<Product> findByCategory(String category);


    @Query(value="select * from Product p where p.Brand like %:keyword% or p.Cost like %:keyword% ", nativeQuery = true)
    List<Product> findByKeyword(@Param("keyword") String keyword);




}
