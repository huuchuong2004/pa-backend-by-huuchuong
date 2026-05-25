package vn.huuchuong.pabackbyhuuchuong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.huuchuong.pabackbyhuuchuong.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByName(String name);
}
