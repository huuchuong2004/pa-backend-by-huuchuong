package vn.huuchuong.pabackbyhuuchuong.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import vn.huuchuong.pabackbyhuuchuong.entity.Role;

@Data
public class UserRequestRegister {


    @NotBlank(message = "Username is required")
    @Schema(description = "Tên đăng nhập", example = "nguyenvana")
    private String username;

   @NotBlank(message = "Password is required")
   @Schema(description = "Mật Khẩu", example = "12345678")
   @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @Size(min=9, message = "Confirm password must be at least 9 characters")
    @Schema(description = "Địa Chỉ Email", example = "nghianau@gmai.com")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone is required")
    @Schema(description = "Số Điện Thoại", example = "0845670498")
    @Size(min = 10 , max = 13 , message = "Phone number must be between 10 and 13 characters")
    private String phone;


    @NotBlank(message = "Fullname is required")
    @Schema(description = "Họ Và Tên", example = "Nẩu Thanh Nghĩa")
    private String fullName;
}
