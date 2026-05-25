package vn.huuchuong.pabackbyhuuchuong.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import vn.huuchuong.pabackbyhuuchuong.base.BaseResponse;
import vn.huuchuong.pabackbyhuuchuong.payload.request.UserLoginRequest;
import vn.huuchuong.pabackbyhuuchuong.payload.request.UserRequestRegister;
import vn.huuchuong.pabackbyhuuchuong.payload.response.AuthResponse;

public interface UserService {
    Void register(@Valid UserRequestRegister userRequestRegister);

    BaseResponse<AuthResponse> login(@Valid UserLoginRequest u, HttpServletRequest httpReq);
}
