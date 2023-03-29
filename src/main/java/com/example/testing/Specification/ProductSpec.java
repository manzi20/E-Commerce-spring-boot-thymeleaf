package com.example.testing.Specification;

import com.example.testing.model.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpec {

    public  static Specification<Product> filterProducts(String search)
    {
        if (search == null)
            return null;

//        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(
//                root.get("Brand"), "%" + search + "%")

        return ((root, query, criteriaBuilder) -> {
            Predicate brand = criteriaBuilder.like(root.get("Brand"), "%" + search + "%");
            Predicate name = criteriaBuilder.like(root.get("Cost"), "%" + search + "%");
            Predicate cat= criteriaBuilder.like(root.get("category"),"%"+search+"%");
            return criteriaBuilder.or(brand, name, cat);
        }
        );
    }

//    public static Specifications<Product> filterProducts(String search) {
//        if (search == null)
//            return null;
//
//        return ((root, query, criteriaBuilder) -> {
//            Predicate brandLike = criteriaBuilder.like(root.get("Brand"), "%" + search + "%");
//            Predicate nameLike = criteriaBuilder.like(root.get("Name"), "%" + search + "%");
//            return criteriaBuilder.or(brandLike, nameLike);
//        }
//        );
//    }

}
