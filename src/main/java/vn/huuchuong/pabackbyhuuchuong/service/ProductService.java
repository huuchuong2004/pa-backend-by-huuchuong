package vn.huuchuong.pabackbyhuuchuong.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.huuchuong.pabackbyhuuchuong.entity.Product;
import vn.huuchuong.pabackbyhuuchuong.payload.request.CreateProductRequest;
import vn.huuchuong.pabackbyhuuchuong.payload.request.UpdateProductRequest;

import java.util.List;

public interface ProductService {

    Page<Product> getAll(Pageable pageable);

    Boolean createProduct(CreateProductRequest createProductRequest);

    Product getDetailProduct(int id);

    Boolean update(int id, @Valid UpdateProductRequest updateProductRequest);

    Boolean delete(int id);
}
