package vn.huuchuong.pabackbyhuuchuong.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.huuchuong.pabackbyhuuchuong.base.BaseResponse;
import vn.huuchuong.pabackbyhuuchuong.payload.request.UserLoginRequest;
import vn.huuchuong.pabackbyhuuchuong.payload.request.UserRequestRegister;
import vn.huuchuong.pabackbyhuuchuong.payload.response.AuthResponse;
import vn.huuchuong.pabackbyhuuchuong.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User API", description = "Quản lý user")
public class UserController {

    private final UserService userService;


    @PostMapping
    @Operation(summary = "Đăng Kí")
    public ResponseEntity<BaseResponse<Void>> register(@Valid @RequestBody UserRequestRegister userRequestRegister){
            return ResponseEntity.ok(new BaseResponse<>(userService.register(userRequestRegister),"Tạo Tài Khoản Thành Công "));
    }


    @PostMapping("/login")
    @Operation(summary = "Đăng nhập")
    public ResponseEntity<BaseResponse<BaseResponse<AuthResponse>>> login (@Valid @RequestBody UserLoginRequest u, HttpServletRequest httpReq){
        return ResponseEntity.ok(new BaseResponse<>(userService.login(u,httpReq),"Đăng Nhập Thành Công"));
    }

}
