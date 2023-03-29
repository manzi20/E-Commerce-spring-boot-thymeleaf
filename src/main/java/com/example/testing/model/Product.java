package com.example.testing.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name=" product")

public class Product  extends AuditModel{
    private static final long serialVersionUID = 1L;
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
