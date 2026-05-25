package vn.huuchuong.pabackbyhuuchuong.payload.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateProductRequest {


    private String name;

    private String description;

    private String linkImg;


}
