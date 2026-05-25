package vn.huuchuong.pabackbyhuuchuong.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String description;

    private String linkImg;

    @OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL, orphanRemoval = true) // phai co nay thi variant moi tu dn gluu
    private List<ProductVariant> variants;

}
