package com.example.testing.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name=" product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ProductName;

    private String Brand;
    private String Picture;
    private String Cost;
    @Column(length = 1000)
    private String Description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Category_id")
    private Category category;

    @Transient
    public String getPhotosImagePath() {
        if (Picture == null || id == null) return null;

        return "/product-photos/" + id + "/" + Picture;
    }

}
