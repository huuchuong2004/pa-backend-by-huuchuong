package vn.huuchuong.pabackbyhuuchuong.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateProductVariantRequest {


        @NotBlank(message = "Phải nhập màu sắc cho sản phẩm")
        @Schema(description = "Màu sắc", example = "Blue")
        private String color;

        @NotBlank(message = "Nhập kích thước là bắt buộc")
        @Schema(description = "Kích thước", example = "L")
        private String size;

        @Min(0)
        @NotBlank(message="Giá sản phẩm phải hợp lệ")
        @Schema(description = "Giá sản phẩm", example = "250000")
        private double price;

        @Min(0)
        @NotBlank(message = "Nhập số lượng tồn kho là bắt buộc")
        @Positive(message = "Số lượng tồn kho phải lớn hơn 0")
        @Schema(description = "Số lượng tồn kho", example = "100")
        private int quantity;

}
