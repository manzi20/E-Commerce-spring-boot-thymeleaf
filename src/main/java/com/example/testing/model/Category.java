package com.example.testing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@Table(name="cat")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CategoryId;

    private String CategoryName;

//    private String enabled;
//    private String children;

    @OneToMany(mappedBy = "category")
    private Set<Product> categoryProducts ;

}
