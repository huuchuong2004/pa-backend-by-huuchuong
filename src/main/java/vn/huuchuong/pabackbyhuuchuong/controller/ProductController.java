package vn.huuchuong.pabackbyhuuchuong.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.huuchuong.pabackbyhuuchuong.base.BaseResponse;
import vn.huuchuong.pabackbyhuuchuong.entity.Product;
import vn.huuchuong.pabackbyhuuchuong.payload.request.CreateProductRequest;
import vn.huuchuong.pabackbyhuuchuong.payload.request.UpdateProductRequest;
import vn.huuchuong.pabackbyhuuchuong.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<BaseResponse<Page<Product>>> getAllProducts(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable

    ) {
        return ResponseEntity.ok(new BaseResponse<>(productService.getAll(pageable),"Lấy danh sách sản phẩm thành công"));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ADMIN')")
    public ResponseEntity<BaseResponse<Boolean>> createProduct( @Valid @RequestBody CreateProductRequest createProductRequest) {
        return ResponseEntity.ok(new BaseResponse<>(productService.createProduct(createProductRequest),"Tạo sản phẩm thành công"));

    }

    @GetMapping("/getDetail/{id}")
    public ResponseEntity<BaseResponse<Product>> getDetailProduct(@PathVariable int id) {
        return ResponseEntity.ok(new BaseResponse<>(productService.getDetailProduct(id),"Lấy chi tiết sản phẩm thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Boolean>> updateProduct(@PathVariable int id ,@Valid @RequestBody UpdateProductRequest updateProductRequest) {
        return ResponseEntity.ok(new BaseResponse<>(productService.update(id,updateProductRequest),"Cập Nhật chi tiết sản phẩm thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Boolean>> deleteProduct(@PathVariable int id) {
        return ResponseEntity.ok(new BaseResponse<>(productService.delete(id),"Xóa sản phẩm thành công"));
    }

}
