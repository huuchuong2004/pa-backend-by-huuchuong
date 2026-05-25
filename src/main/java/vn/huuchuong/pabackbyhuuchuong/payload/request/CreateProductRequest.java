package vn.huuchuong.pabackbyhuuchuong.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import vn.huuchuong.pabackbyhuuchuong.entity.ProductVariant;

import java.util.List;

@Data

public class CreateProductRequest {



    @Size(min = 1, max = 100)
    @NotBlank(message = "Product name không được để trống")
    @Schema(description = "Tên Sản Phẩm", example = "Quần Jean")
    private String name;


    @Size(min = 10, max = 1000)
    @NotBlank(message = "Mô tả không được để trống")
    @Schema(description = "Mô Tả cho sản phẩm", example = "Quần Jean sản xuất từ nhà Nghĩa")
    private String description;


    @NotBlank(message = "Link ảnh  không được để trống")
    @Schema(description = "Link Hình Ảnh", example = "https://example.com/image.jpg")
    private String linkImg;


    @OneToMany(mappedBy = "product")
    private List<CreateProductVariantRequest> variants;
}
