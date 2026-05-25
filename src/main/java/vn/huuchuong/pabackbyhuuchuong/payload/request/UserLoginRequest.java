package vn.huuchuong.pabackbyhuuchuong.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserLoginRequest {
    @Schema(description = "Tên đăng nhập", example = "nghianau")
    private String username;
    @Schema(description = "Password", example = "12345678")
    private String password;
}
