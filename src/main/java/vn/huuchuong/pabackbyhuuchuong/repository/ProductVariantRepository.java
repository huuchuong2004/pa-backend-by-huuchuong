package vn.huuchuong.pabackbyhuuchuong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.huuchuong.pabackbyhuuchuong.entity.ProductVariant;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Integer> {
    List<ProductVariant> findByProductId(int id);

    List<ProductVariant> findAllByProductId(int id);
}
